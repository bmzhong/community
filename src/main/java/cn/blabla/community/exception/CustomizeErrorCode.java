package cn.blabla.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    Question_NOT_FOUND("你的问题找不到了，要不要换个试试");

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    CustomizeErrorCode(String message) {
        this.message = message;
    }

}
