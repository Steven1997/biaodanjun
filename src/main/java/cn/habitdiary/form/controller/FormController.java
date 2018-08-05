package cn.habitdiary.form.controller;

import cn.habitdiary.form.entity.Form;
import cn.habitdiary.form.entity.FormDefinition;
import cn.habitdiary.form.entity.User;
import cn.habitdiary.form.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

/**
 * 表单控制器
 */
@Controller
public class FormController {
            @Autowired
            private FormService formService;

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
                                 @RequestParam("iteminfo[]") String iteminfo, HttpSession session,Model model){
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
                Form form = formService.selectForm(uuid,null,userid);
                modelAndView.setViewName("fill");
                modelAndView.addObject("formDefinition",formDefinition);
                modelAndView.addObject("form",form);
                return modelAndView;
             }
}
