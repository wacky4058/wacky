package com.shao.wacky.domain;

import java.util.Arrays;

/**
 * service层日记打印对象
 */
public class ServiceLogDomain {
    /**
     *
     */
    private String statrTime;
    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 请求类
     */
    private String requestClass;

    /**
     * 请求对象方法
     */
    private String requestMethod;
    /**
     * 请求参数
     */
    private String requestArgs;

    /**
     * 返回结果对象
     */
    private Object result;

    public String getStatrTime() {
        return statrTime;
    }

    public void setStatrTime(String statrTime) {
        this.statrTime = statrTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public String getRequestArgs() {
        return requestArgs;
    }

    public void setRequestArgs(String requestArgs) {
        this.requestArgs = requestArgs;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getRequestClass() {
        return requestClass;
    }

    public void setRequestClass(String requestClass) {
        this.requestClass = requestClass;
    }

    @Override
    public String toString() {
        return "ServiceLogDomain{" +
                " 请求开始时间：'" + statrTime + '\'' +
                ", 请求类：'" + requestClass + '\'' +
                ", 请求方法：'" + requestMethod + '\'' +
                ", 请求参数：{ '" + requestArgs + '\''+"}"+
                ", 返回结果：" + result+
                ", 请求结束时间：'" + endTime + '\'' +
                '}';
    }
}
