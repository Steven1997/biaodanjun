package cn.habitdiary.form.service;

import cn.habitdiary.form.entity.Form;
import cn.habitdiary.form.entity.FormDefinition;
import cn.habitdiary.form.entity.User;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 表单业务接口
 */
public interface FormService {
    /**
     * 添加表单
     * @param formname
     * @param formdesc
     * @param begintime
     * @param endtime
     * @param password
     * @param loginUser
     * @param name
     * @param info
     * @return
     */
    public String addForm(String formname,String formdesc,String begintime,
                        String endtime,String password,User loginUser,
                        String[] name,String[] info);

    /**
     * 获取用户定义
     * @param userid
     * @param uuid
     * @return
     */
    public FormDefinition getFormDefinition(Integer userid,String uuid);

    /**
     * 获取表单
     * @param formid
     * @param uuid
     * @param formname
     * @param userid
     * @return
     */
    public Form selectForm(Integer formid,String uuid,String formname,Integer userid);

    /**
     * 查询表单集合
     * @param formid
     * @param uuid
     * @param formname
     * @param userid
     * @return
     */
    public List<Form> listForm(Integer formid, String uuid, String formname, Integer userid);


    /**
     * 检查表单密码
     * @param password
     * @param formid
     * @return
     */
    public boolean checkPassword(String password,Integer formid);

    /**
     * 修改表单状态
     * @param formid
     */
    public void changeFormStatus(Integer formid,Integer nowstatus);

    /**
     * 删除表单
     * @param formid
     */
    public void delForm(Integer formid);

    /**
     * 下载表单
     * @param formid
     * @return
     */
    public ResponseEntity<byte[]> downloadForm(Integer formid);



}
