package cn.habitdiary.form.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 主页路由控制
 */
@Controller
public class IndexController {
    /**
     * 访问设计表单页面
     * @return
     */
    @GetMapping(value = "/design")
    public String design(){
        return "design";
    }

    /**
     * 访问搜索表单页面
     * @return
     */
    @GetMapping(value = "/search")
    public String search() {
        return "search";
    }

    /**
     * 访问我的表单页面
     * @return
     */
    @GetMapping(value = "/myform")
    public String myform() {
        return "myform";
    }

    /**
     * 访问首页
     * @return
     */
    @GetMapping(value = {"/","/index"})
    public String index() {
        return "index";
    }

    /**
     * 访问登录页面
     * @return
     */
    @GetMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    /**
     * 访问注册页面
     * @return
     */
    @GetMapping(value = {"/register"})
    public String register() {
        return "register";
    }
}
