package cn.habitdiary.form.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FormDao {
    public void addForm(@Param("uuid") String uuid, @Param("formname") String formname,
                        @Param("formpath") String formpath, @Param("formstatus") Integer formstatus,
                        @Param("formdesc") String formdesc, @Param("begintime") String begintime,
                        @Param("endtime") String endtime, @Param("userid") Integer userid,
                        @Param("password") String password);
}
