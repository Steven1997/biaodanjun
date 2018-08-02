package cn.habitdiary.form.service;


import cn.habitdiary.form.entity.User;

public interface UserService {
    public boolean checkUser(String username);
    public boolean checkEmail(String email);
    public User selectUser(Integer userid,String username,String email);
    public void addUser(User user);
}
