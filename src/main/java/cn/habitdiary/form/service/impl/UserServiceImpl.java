package cn.habitdiary.form.service.impl;

import cn.habitdiary.form.dao.UserDao;
import cn.habitdiary.form.entity.User;
import cn.habitdiary.form.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean checkUser(String username) {
        if(userDao.selectUser(null,username,null) != null)
            return true;
        return false;
    }

    @Override
    public boolean checkEmail(String email) {
        if(userDao.selectUser(null,null,email) != null)
            return true;
        return false;
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public User selectUser(Integer userid, String username, String email) {
        return userDao.selectUser(userid,username,email);
    }
}
