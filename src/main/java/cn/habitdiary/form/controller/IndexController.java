package cn.habitdiary.form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/design")
    public String design(){
        return "design";
    }

    @GetMapping(value = "/search")
    public String search() {
        return "search";
    }

    @GetMapping(value = "/myform")
    public String myform() {
        return "myform";
    }

    @GetMapping(value = {"/","/index"})
    public String index() {
        return "index";
    }

    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/register"})
    public String register() {
        return "register";
    }
}
