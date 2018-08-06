package cn.habitdiary.form.controller;

import cn.habitdiary.form.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    /**
     * 写入一条反馈
     * @param itemValue
     * @param uuid
     * @param userid
     * @param formname
     * @return
     */
    @PostMapping("/doCollect")
    public String doCollect(@RequestParam("itemValue") String itemValue, @RequestParam("uuid") String uuid,
                            @RequestParam("userid") Integer userid, @RequestParam("formname") String formname,
                            @RequestParam("formid") Integer formid) {

        String[] items = itemValue.split(",");
        feedbackService.fillForm(formid,uuid,userid,formname,items);
        return "index";
    }
}
