package cn.habitdiary.form.dao;

import cn.habitdiary.form.entity.Feedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackDao {
    /**
     * 添加一条反馈
     * @param formid
     * @param rownumber
     * @param feedbacktime
     * @param feedbackstatus
     */
    public void addFeedback(@Param("formid") Integer formid,@Param("rownumber")Integer rownumber,@Param("feedbacktime") String feedbacktime,@Param("feedbackstatus") Integer feedbackstatus);

    /**
     * 查询反馈
     * @param feedbackid
     * @param formid
     * @return
     */
    public Feedback selectFeedback(@Param("feedbackid") Integer feedbackid, @Param("formid") Integer formid,@Param("feedbackstatus") Integer feedbackstatus);
}
