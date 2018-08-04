package cn.habitdiary.form.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 持久化对象 保存表单的字段和描述，用于表单界面的渲染
 */
public class FormDefinition implements Serializable {
        private static final long serialVersionUID = 1L;
        private String[] itemsName; //表单项名
        private String[] itemsInfo; //表单项提示信息

    public FormDefinition(String[] itemsName, String[] itemsInfo) {
        this.itemsName = itemsName;
        this.itemsInfo = itemsInfo;
    }

    public String[] getItemsName() {
        return itemsName;
    }

    public void setItemsName(String[] itemsName) {
        this.itemsName = itemsName;
    }

    public String[] getItemsInfo() {
        return itemsInfo;
    }

    public void setItemsInfo(String[] itemsInfo) {
        this.itemsInfo = itemsInfo;
    }
}
