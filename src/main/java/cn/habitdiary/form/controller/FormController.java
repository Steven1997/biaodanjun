package cn.habitdiary.form.controller;

import cn.habitdiary.form.entity.Form;
import cn.habitdiary.form.entity.FormDefinition;
import cn.habitdiary.form.entity.User;
import cn.habitdiary.form.service.FormService;
import cn.habitdiary.form.utils.EmailUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * 表单控制器
 */
@Controller
public class FormController {
            @Autowired
            private FormService formService;

            @Autowired
            private ThreadPoolTaskScheduler threadPoolTaskScheduler;

            @Autowired
            private EmailUtil emailUtil;

            private ScheduledFuture<?> future;

            @Bean
            public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
                return new ThreadPoolTaskScheduler();
            }

           /* @RequestMapping("/startCron")
            public String startCron(@RequestParam("cron") String cron,@RequestParam("username") String username,
                                    @RequestParam("formname") String formname,@RequestParam("date") String date,@RequestParam("toBody") String toBody) {
                future = threadPoolTaskScheduler.schedule(new MyRunnable(username,formname,date,toBody), new CronTrigger(cron));
                System.out.println("DynamicTask.startCron()");
                return "index";
            }

            @RequestMapping("/stopCron")
            public String stopCron() {
                if (future != null) {
                    future.cancel(true);
                }
                System.out.println("DynamicTask.stopCron()");
                return "index";
            }*/



            private class MyRunnable implements Runnable {
                private String username;
                private String formname;
                private String date;
                private String toBody;

                public MyRunnable(String username, String formname, String date, String toBody) {
                    this.username = username;
                    this.formname = formname;
                    this.date = date;
                    this.toBody = toBody;
                }

                @Override
                public void run() {
                    Map<String,Object> mp = new HashMap<>();
                    mp.put("username",username);
                    mp.put("formname",formname);
                    mp.put("date",date);
                    emailUtil.sendTemplateMail(toBody,"表单君表单收集结束提醒","reminder",mp);
                }
            }


            /**
              * 设置表单基本信息
              * @param map
              * @param model
              * @return
              */
            @PostMapping("/doDesign")
            public String doDesign(@RequestParam Map<String,String> map, Model model){
                  String formname = map.get("formname");
                  String formdesc = map.get("formdesc");
                  String begintime = map.get("begintime");
                  String endtime = map.get("endtime");
                  String password = map.get("password");
                  model.addAttribute("formname",formname);
                  model.addAttribute("formdesc",formdesc);
                  model.addAttribute("begintime",begintime);
                  model.addAttribute("endtime",endtime);
                  model.addAttribute("password",password);
                  return "edit";
            }

            /**
             * 设计表单项信息
             * @param map
             * @param itemname
             * @param iteminfo
             * @param session
             * @param model
             * @return
             */
            @PostMapping("/doEdit")
            public String doEdit(@RequestParam Map<String,String> map, @RequestParam("itemname[]") String itemname,
                                 @RequestParam("iteminfo[]") String iteminfo, HttpSession session,Model model) throws ParseException {
                String formname = map.get("formname").trim();
                String formdesc = map.get("formdesc").trim();
                String begintime = map.get("begintime").trim();
                String endtime = map.get("endtime").trim();
                String password = map.get("password").trim();
                String[] name = itemname.split(",");
                String[] info = iteminfo.split(",");
                User loginUser = (User) session.getAttribute("loginUser");
                String uuid = formService.addForm(formname,formdesc,begintime,endtime,password,loginUser,name,info);
                model.addAttribute("title",formname);
                model.addAttribute("link","http://localhost:8080/f/" + loginUser.getUserid() + "/" + uuid);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date endDate = simpleDateFormat.parse(endtime);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(endDate);
                String cron = "0 " + calendar.get(Calendar.MINUTE) + " " + calendar.get(Calendar.HOUR_OF_DAY) + " " + calendar.get(Calendar.DAY_OF_MONTH) + " " + (calendar.get(Calendar.MONTH) + 1) + " ?";
                String date = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
                System.out.println(cron);
                future = threadPoolTaskScheduler.schedule(new MyRunnable(loginUser.getUsername(),formname,date,loginUser.getEmail()), new CronTrigger(cron));
                return "edit";
            }

            /**
             * 根据表单定义渲染表单页面
             * @param userid
             * @param uuid
             * @return
             */
             @GetMapping("/f/{userid}/{uuid}")
             public ModelAndView fillBlank(@PathVariable("userid") Integer userid, @PathVariable("uuid") String uuid) {
                ModelAndView modelAndView = new ModelAndView();
                FormDefinition formDefinition = formService.getFormDefinition(userid,uuid);
                Form form = formService.selectForm(null,uuid,null,userid);
                modelAndView.setViewName("fill");
                modelAndView.addObject("formDefinition",formDefinition);
                modelAndView.addObject("form",form);
                modelAndView.addObject("userid",userid);
                return modelAndView;
             }

            /**
             * 检验表单密码是否正确
             * @param json
             * @return
             * @throws JSONException
             */
            @PostMapping(value = "/checkPassword", produces = "application/json;charset=utf-8")
            @ResponseBody
            public String checkPassword(@RequestBody String json) throws JSONException {
                JSONObject jsonObject = new JSONObject(json);
                JSONObject data = new JSONObject();
                String password = (String)jsonObject.get("password");
                String str = (String)jsonObject.get("formid");
                Integer formid = Integer.valueOf(str);
                if(formService.checkPassword(password,formid)){
                    data.put("msg","ok");
                }
                else{
                    data.put("msg","no");
                }
                return data.toString();
            }










}
