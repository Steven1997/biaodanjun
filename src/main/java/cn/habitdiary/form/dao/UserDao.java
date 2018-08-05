package cn.habitdiary.form.dao;

import cn.habitdiary.form.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 用户Dao层
 */
@Repository
public interface UserDao {
    /**
     * 查询用户
     * @param userid
     * @param username
     * @param email
     * @return
     */
    public User selectUser(@Param("userid") Integer userid,@Param("username") String username,@Param("email") String email);

    /**
     * 添加用户
     * @param user
     */
    public void addUser(User user);
}
