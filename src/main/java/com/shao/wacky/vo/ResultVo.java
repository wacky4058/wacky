package com.shao.wacky.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo<T> implements Serializable {
    private int code;

    private String message;

    private T data;

    public ResultVo(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultVo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }



    /**
     * 返回成功
     * @return 公共返回类
     */
    public static <T> ResultVo<T> ok(){
        return new ResultVo<>(200, "操作成功");
    }

    /**
     * 返回成功
     * @param message 信息
     * @return 公共返回类
     */
    public static <T> ResultVo<T> ok(String message){
        return new ResultVo<>(200, message);
    }

    /**
     * 返回成功
     * @param <T> 数据
     * @return 公共返回类
     */
    public static <T> ResultVo<T> ok(T data){
        return new ResultVo<>(200,"操作成功", data);
    }

    /**
     * 返回成功
     * @param message 信息
     * @param <T> 数据
     * @return 公共返回类
     */
    public static <T> ResultVo<T> ok(String message, T data){
        return new ResultVo<>(200, message, data);
    }

    /**
     * 返回失败
     * @param message 信息
     * @return 公共返回类
     */
    public static <T> ResultVo<T> fail(String message){
        return new ResultVo<>(500, message);
    }

    /**
     * 返回失败
     * @param message 信息
     * @return 公共返回类
     */
    public static <T> ResultVo<T> fail(int code,String message){
        return new ResultVo<>(code, message);
    }


}
