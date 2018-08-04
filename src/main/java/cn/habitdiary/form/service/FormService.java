package cn.habitdiary.form.service;

import cn.habitdiary.form.entity.User;

public interface FormService {
    public void addForm(String formname,String formdesc,String begintime,
                        String endtime,String password,User loginUser,
                        String[] name,String[] info);

}
