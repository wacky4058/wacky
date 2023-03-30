package com.shao.wacky.exception;

public class WackyException extends RuntimeException{

    private int code;
    private String message;

    public WackyException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public WackyException(ResultStatusEnum resultStatusEnum) {
        this.code = resultStatusEnum.getCode();
        this.message = resultStatusEnum.getMessage();
    }

    public WackyException(String message) {
        this.code = 500;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
