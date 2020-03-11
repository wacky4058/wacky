package com.shao.wacky.exception;

public enum ResultStatusEnum {
    /**
     * 请求成功
     */
    SUCCESS(200, "请求成功"),

    /**
     * 密码错误
     */
    PASSWORD_NOT_MATCHING(400, "密码错误");


    private int code;

    private String message;

    ResultStatusEnum(int s, String str) {
        this.code=s;
        this.message=str;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
