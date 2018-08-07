package cn.habitdiary.form.timertask;

import cn.habitdiary.form.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

/**
 * 邮件发送定时器
 */
public class MailTimerTask extends TimerTask {
    @Autowired
    private EmailUtil emailUtil;

    private String username;

    private String formname;

    private String date;

    private String toBody;


    @Override
    public void run() {
        Map<String,Object> mp = new HashMap<>();
        mp.put("username",username);
        mp.put("formname",formname);
        emailUtil.sendTemplateMail(toBody,"提醒邮件","reminder",mp);
    }
}
