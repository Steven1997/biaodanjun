package cn.habitdiary.form.enums;

public enum FeedbackStatusEnum {
    TODO("待处理"),
    DONE("已处理");

    private String statusInfo;

    FeedbackStatusEnum(String statusInfo) {
        this.statusInfo = statusInfo;
    }

    public String getStatusInfo() {
        return statusInfo;
    }

    public void setStatusInfo(String statusInfo) {
        this.statusInfo = statusInfo;
    }
}
