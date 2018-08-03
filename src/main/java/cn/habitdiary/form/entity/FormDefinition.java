package cn.habitdiary.form.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 持久化对象 保存表单的字段和描述，用于表单界面的渲染
 */
public class FormDefinition implements Serializable {
        List<String> itemsName; //表单项名
        List<String> itemsInfo; //表单项提示信息

    public List<String> getItemsName() {
        return itemsName;
    }

    public void setItemsName(List<String> itemsName) {
        this.itemsName = itemsName;
    }

    public List<String> getItemsInfo() {
        return itemsInfo;
    }

    public void setItemsInfo(List<String> itemsInfo) {
        this.itemsInfo = itemsInfo;
    }
}
