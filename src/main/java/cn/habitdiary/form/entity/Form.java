package cn.habitdiary.form.entity;

import java.io.Serializable;
import java.util.List;


public class Form implements Serializable {
        private Integer formid; //表单编号
        private String uuid; //表单序列号
        private String formname; //表单名
        private String formpath; //表单路径
        private Integer formstatus; //表单状态:0表示未开始,1表示收集中,2表示已结束
        private String formdesc; //表单描述
        private String begintime; //开始时间
        private String endtime; //结束时间
        private User createUser; //创建用户
        private String password; //填写密码
        private List<Feedback> feedbacks; //反馈列表

    public Form() {
    }

    public Integer getFormid() {
        return formid;
    }

    public void setFormid(Integer formid) {
        this.formid = formid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFormname() {
        return formname;
    }

    public void setFormname(String formname) {
        this.formname = formname;
    }

    public String getFormpath() {
        return formpath;
    }

    public void setFormpath(String formpath) {
        this.formpath = formpath;
    }

    public String getFormdesc() {
        return formdesc;
    }

    public void setFormdesc(String formdesc) {
        this.formdesc = formdesc;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Integer getFormstatus() {
        return formstatus;
    }

    public void setFormstatus(Integer formstatus) {
        this.formstatus = formstatus;
    }
}
