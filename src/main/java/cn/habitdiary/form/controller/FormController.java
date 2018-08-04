package cn.habitdiary.form.controller;

import cn.habitdiary.form.entity.User;
import cn.habitdiary.form.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Map;

@Controller
public class FormController {
            @Autowired
            private FormService formService;
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

            @PostMapping("/doEdit")
            public String doEdit(@RequestParam Map<String,String> map, @RequestParam("itemname[]") String itemname,
                                 @RequestParam("iteminfo[]") String iteminfo, HttpSession session){
                String formname = map.get("formname").trim();
                String formdesc = map.get("formdesc").trim();
                String begintime = map.get("begintime").trim();
                String endtime = map.get("endtime").trim();
                String password = map.get("password").trim();
                String[] name = itemname.split(",");
                String[] info = iteminfo.split(",");
                User loginUser = (User) session.getAttribute("loginUser");
                formService.addForm(formname,formdesc,begintime,endtime,password,loginUser,name,info);
                return "index";
            }
}
