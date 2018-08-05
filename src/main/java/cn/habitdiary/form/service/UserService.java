package cn.habitdiary.form.service;


import cn.habitdiary.form.entity.User;

/**
 * 用户业务接口
 */
public interface UserService {
    /**
     * 检验用户名是否被注册
     * @param username
     * @return
     */
    public boolean checkUser(String username);

    /**
     * 检验邮箱是否被绑定
     * @param email
     * @return
     */
    public boolean checkEmail(String email);

    /**
     * 查询用户
     * @param userid
     * @param username
     * @param email
     * @return
     */
    public User selectUser(Integer userid,String username,String email);

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);
}
