package cn.habitdiary.form.dao;

import cn.habitdiary.form.entity.Form;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 表单Dao层
 */
@Repository
public interface FormDao {
    /**
     * 添加表单
     * @param uuid
     * @param formname
     * @param formpath
     * @param formstatus
     * @param formdesc
     * @param begintime
     * @param endtime
     * @param userid
     * @param password
     */
    public void addForm(@Param("uuid") String uuid, @Param("formname") String formname,
                        @Param("formpath") String formpath, @Param("formstatus") Integer formstatus,
                        @Param("formdesc") String formdesc, @Param("begintime") String begintime,
                        @Param("endtime") String endtime, @Param("userid") Integer userid,
                        @Param("password") String password);

    /**
     * 查询一个表单
     * @param formid
     * @param uuid
     * @param formname
     * @param userid
     * @return
     */
    public Form selectForm(@Param("formid") Integer formid,@Param("uuid") String uuid, @Param("formname") String formname, @Param("userid") Integer userid);

    /**
     * 查询表单集合
     * @param formid
     * @param uuid
     * @param formname
     * @param userid
     * @return
     */
    public List<Form> listForm(@Param("formid") Integer formid, @Param("uuid") String uuid, @Param("formname") String formname, @Param("userid") Integer userid);

    /**
     * 修改表单状态
     * @param formid
     * @param formstatus
     */
    public void updateFormStatus(@Param("formid") Integer formid,@Param("formstatus") Integer formstatus);

    /**
     * 删除表单
     * @param formid
     */
    public void deleteForm(@Param("formid") Integer formid);


    /**
     * 模糊搜素表单
     * @param formname
     * @param username
     * @return
     */
    public List<Form> searchForm(@Param("formname") String formname,@Param("username") String username);
}
