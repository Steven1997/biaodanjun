package cn.habitdiary.form.controller;

import cn.habitdiary.form.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    /**
     * 添加一条反馈
     * @param itemValue
     * @param uuid
     * @param userid
     * @param formname
     * @return
     */
    @PostMapping("/doCollect")
    public String doCollect(@RequestParam("itemValue") String itemValue, @RequestParam("uuid") String uuid,
                            @RequestParam("userid") Integer userid, @RequestParam("formname") String formname,
                            @RequestParam("formid") Integer formid,Model model) {

        String[] items = itemValue.split(",");
        feedbackService.fillForm(formid,uuid,userid,formname,items);
        model.addAttribute("uuid",uuid);
        model.addAttribute("userid",userid);
        model.addAttribute("formname",formname);
        return "success";
    }



}
