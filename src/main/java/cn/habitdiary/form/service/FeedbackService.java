package cn.habitdiary.form.service;

import cn.habitdiary.form.entity.Feedback;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 查询反馈集合
     * @param feedbackid
     * @param formid
     * @param feedbackstatus
     * @return
     */
    public List<Feedback> listFeedbacks(Integer feedbackid, Integer formid, Integer feedbackstatus);
    /**
     * 读取反馈内容
     * @param userid
     * @param uuid
     * @param formname
     * @param rownumber
     * @return
     */
    public String[] getFillResult(Integer userid,String uuid,String formname,Integer rownumber);


    /**
     * 设置反馈状态
     * @param feedbackid
     */
    public void changeStatus(Integer feedbackid);
}
