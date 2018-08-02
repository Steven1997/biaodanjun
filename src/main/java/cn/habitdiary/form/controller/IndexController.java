package cn.habitdiary.form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

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
