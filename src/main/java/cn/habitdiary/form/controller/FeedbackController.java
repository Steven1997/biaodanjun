package cn.habitdiary.form.controller;

import cn.habitdiary.form.entity.Feedback;
import cn.habitdiary.form.entity.Form;
import cn.habitdiary.form.entity.FormDefinition;
import cn.habitdiary.form.service.FeedbackService;
import cn.habitdiary.form.service.FormService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private FormService formService;
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

    /**
     * 查看表单详情
     * @param formid
     * @return
     */
    @GetMapping("/forminfo")
    public String feedbackList(@RequestParam("formid") Integer formid,@RequestParam(required=true,defaultValue="1") Integer page,@RequestParam(required=true,defaultValue="5") Integer pagesize,Model model) {

        Form form = formService.selectForm(formid,null,null,null);
        PageHelper.startPage(page,pagesize);
        List<Feedback> feedbackList = feedbackService.listFeedbacks(null,formid,null);
        PageInfo<Feedback> p = new PageInfo<Feedback>(feedbackList);
        model.addAttribute("page", p);
        model.addAttribute("feedbackList",feedbackList);
        model.addAttribute("form",form);
        model.addAttribute("title",form.getFormname());
        String link = "/f/" + form.getCreateUser().getUserid() + "/" + form.getUuid();
        model.addAttribute("link",link);
        return "forminfo";
    }

    /**
     * 查看填写情况
     * @return
     */
    @GetMapping("/checkFeedback")
    public ModelAndView checkFeedback(@RequestParam("rownumber") Integer rownumber,@RequestParam("userid") Integer userid,
                                        @RequestParam("uuid") String uuid,@RequestParam("feedbackid") Integer feedbackid) {
        ModelAndView modelAndView = new ModelAndView();
        FormDefinition formDefinition = formService.getFormDefinition(userid,uuid);
        Form form = formService.selectForm(null,uuid,null,userid);
        String formname = form.getFormname();
        String[] result = feedbackService.getFillResult(userid,uuid,formname,rownumber);
        feedbackService.changeStatus(feedbackid);
        modelAndView.setViewName("check");
        modelAndView.addObject("formDefinition",formDefinition);
        modelAndView.addObject("form",form);
        modelAndView.addObject("userid",userid);
        modelAndView.addObject("result",result);
        return modelAndView;
    }

    /**
     * 全部已读
     * @param formid
     * @return
     */
    @GetMapping("/ignore")
    public String ignore(@RequestParam("formid") Integer formid) {
        feedbackService.ignore(formid);
        return "redirect:/forminfo?formid=" + formid;
    }



}
