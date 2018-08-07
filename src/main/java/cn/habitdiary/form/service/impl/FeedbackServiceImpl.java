package cn.habitdiary.form.service.impl;

import cn.habitdiary.form.dao.FeedbackDao;
import cn.habitdiary.form.entity.Feedback;
import cn.habitdiary.form.service.FeedbackService;
import cn.habitdiary.form.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Value("${root-location}")
    private String rootLocation;
    @Autowired
    private FeedbackDao feedbackDao;
    @Override
    public void fillForm(Integer formid,String uuid, Integer userid, String formname, String[] items) {
        String filepath =  rootLocation + "/" + userid + "/" + formname +
                "(" + uuid + ")" + ".xls";
        Integer rownumber = ExcelUtil.fillExcel(filepath,items);
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr =  dft.format(new Date());
        feedbackDao.addFeedback(formid,rownumber,dateStr,0);
    }

    @Override
    public Feedback selectFeedback(Integer feedbackid, Integer formid,Integer feedbackstatus) {
        return feedbackDao.selectFeedback(feedbackid,formid,feedbackstatus);
    }
}
