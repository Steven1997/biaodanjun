package cn.habitdiary.form.enums;

public enum FormStatusEnum {
    PREPARING("未开始"),
    RUNNING("收集中"),
    STOPPED("已结束");

    private String statusInfo;

    FormStatusEnum(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }
}
