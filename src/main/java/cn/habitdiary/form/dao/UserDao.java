package cn.habitdiary.form.dao;

import cn.habitdiary.form.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    public User selectUser(@Param("userid") Integer userid,@Param("username") String username,@Param("email") String email);

    public void addUser(User user);
}
