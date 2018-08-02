package cn.habitdiary.form.controller;
import cn.habitdiary.form.entity.User;
import cn.habitdiary.form.service.UserService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
public class UserController {
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/checkUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkUser(@RequestBody String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject data = new JSONObject();
        String username = (String)jsonObject.get("username");
        if(userService.checkUser(username)){
            data.put("msg","no");
        }
        else{
            data.put("msg","ok");
        }
        return data.toString();
    }

    @PostMapping(value = "/checkEmail", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String checkEmail(@RequestBody String json) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject data = new JSONObject();
        String email = (String)jsonObject.get("email");
        if(userService.checkEmail(email)){
            data.put("msg","no");
        }
        else{
            data.put("msg","ok");
        }
        return data.toString();
    }

    @PostMapping(value = "/doRegister")
    @ResponseBody
    public String doRegister(@Valid User user, BindingResult bindingResult,HttpSession session) throws JSONException {
        JSONObject data = new JSONObject();

        if (bindingResult.hasErrors()) {
            data.put("title","注册失败");
            data.put("content","请重新注册");
            return data.toString();
        }
        String pwd = user.getPassword().trim();
        String username = user.getUsername().trim();
        String email = user.getEmail().trim();
        pwd = DigestUtils.sha1Hex(pwd); //Sha1加密入库
        user = new User(username,pwd,email);
        userService.addUser(user);
        session.setAttribute("loginUser",user);
        data.put("title","注册成功");
        data.put("content","欢迎来到表单君的世界");
        return data.toString();
    }

    @PostMapping(value = "/doLogin", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String doLogin(@RequestBody String json,HttpSession session) throws JSONException {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject data = new JSONObject();
        String username = (String)jsonObject.get("username");
        String password = (String)jsonObject.get("password");
        String shapwd = DigestUtils.sha1Hex(password);
        String code = (String)jsonObject.get("code");
        User user1 = userService.selectUser(null,username,null);
        User user2 = userService.selectUser(null,null,username);
        String kaptchaExpected = (String)session.getAttribute("vrifyCode");
        if(!code.equalsIgnoreCase(kaptchaExpected)){
            data.put("title","登录失败");
            data.put("content","验证码错误");
            return data.toString();
        }
        if(user1 != null){
            if(shapwd.equals(user1.getPassword())){
                    session.setAttribute("loginUser",user1);
                    data.put("title","登录成功");
                    data.put("content","欢迎使用表单君");
            }
            else{
                data.put("title","登录失败");
                data.put("content","用户名或密码错误");
            }
        }
        else if(user2 != null){
            if(shapwd.equals(user2.getPassword())){
                session.setAttribute("loginUser",user2);
                data.put("title","登录成功");
                data.put("content","欢迎使用表单君");
            }
            else{
                data.put("title","登录失败");
                data.put("content","用户名或密码错误");
            }
        }
        else{
            data.put("title","登录失败");
            data.put("content","用户不存在");

        }
        return data.toString();

    }

    @GetMapping("/exit")
    public String exit(HttpSession session, SessionStatus sessionStatus){
        session.invalidate();  //然后是让httpsession失效
        return "index";
    }



    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

}
