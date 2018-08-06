package cn.habitdiary.form.entity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;


/**
 * 表单项实体类，保存表单的字段和描述，用于表单界面的渲染
 */
public class FormItem implements Serializable{
        private static final long serialVersionUID = 1L;
        private String itemName; //表单项名
        private String itemInfo; //表单项提示信息

    public FormItem(String itemName, String itemInfo) {
        this.itemName = itemName;
        this.itemInfo = itemInfo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }


}
