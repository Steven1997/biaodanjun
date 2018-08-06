package cn.habitdiary.form.service;

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
}
