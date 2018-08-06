package cn.habitdiary.form.service;

import cn.habitdiary.form.entity.Feedback;
import org.apache.ibatis.annotations.Param;

/**
 * 反馈业务接口
 */
public interface FeedbackService {
    /**
     * 插入一条反馈
     * @param formid
     * @param uuid
     * @param userid
     * @param formname
     * @param items
     */
    public void fillForm(Integer formid,String uuid,Integer userid,String formname,String[] items);

    /**
     * 查询一条反馈
     * @param feedbackid
     * @param formid
     * @return
     */
    public Feedback selectFeedback(Integer feedbackid, Integer formid,Integer feedbackstatus);
}
