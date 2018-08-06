package cn.habitdiary.form.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

}
