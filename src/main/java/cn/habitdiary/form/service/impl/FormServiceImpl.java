package cn.habitdiary.form.service.impl;

import cn.habitdiary.form.dao.FormDao;
import cn.habitdiary.form.entity.FormDefinition;
import cn.habitdiary.form.entity.User;
import cn.habitdiary.form.service.FormService;
import cn.habitdiary.form.utils.ExcelUtils;
import cn.habitdiary.form.utils.ObjectIOUtils;
import cn.habitdiary.form.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class FormServiceImpl implements FormService {
    @Autowired
    private FormDao formDao;
    @Value("${root-location}")
    private String rootLocation;
    @Override
    public void addForm(String formname, String formdesc, String begintime, String endtime,
                        String password,User loginUser,String[] name,String[] info) {
            String username = loginUser.getUsername();
            Integer userid = loginUser.getUserid();
            Integer formstatus = null;
            File rootdir = new File(rootLocation);
            if (!rootdir.exists()) {
                rootdir.mkdir();
            }
            File userdir = new File(rootLocation + "/" + username);
            if (!userdir.exists()) {
                userdir.mkdir();
            }
            String uuid = UUIDUtils.getUUID();
            String formpath = rootLocation + "/" + username + "/" + formname +
                    "(" + uuid + ")" + ".xls";
            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            long beginLong = fmt.parse(begintime).getTime();
            long endLong = fmt.parse(endtime).getTime();
            long now = System.currentTimeMillis();
            if(now < beginLong){
                formstatus = 0;
            }else if(now <= endLong){
                formstatus = 1;
            }else{
                formstatus = 2;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formDao.addForm(uuid,formname,formpath,formstatus,formdesc,begintime,endtime,userid,password);
        //保存FormDefinition文件，保存Excel文件
        ExcelUtils.createExcel(formname,formpath,name);
        FormDefinition formDefinition = new FormDefinition(name,info);
        String objectPath = formpath.replace("xls","txt");
        try {
            ObjectIOUtils.createObj(objectPath,formDefinition);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
