package cn.habitdiary.form.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 表单定义类，用于序列化保存表单信息
 */
public class FormDefinition implements Serializable{
    private static final long serialVersionUID = 1L;
    private List<FormItem> formItemList;

    public FormDefinition(List<FormItem> formItemList) {
        this.formItemList = formItemList;
    }


    public List<FormItem> getFormItemList() {
        return formItemList;
    }

    public void setFormItemList(List<FormItem> formItemList) {
        this.formItemList = formItemList;
    }


}
