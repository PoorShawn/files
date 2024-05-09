package com.gao.mall.tiny.malltiny01.common;

public class CommonResult<T> {
    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private T data;

    public CommonResult() {
    }

    public CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success() {
        return new CommonResult<T>(200, "success", null);
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(200, "success", data);
    }
    public static <T> CommonResult<T> success(T data, String msg) {
        return new CommonResult<T>(200, msg, data);
    }

    public static <T> CommonResult<T> failure() {
        return new CommonResult<T>(500, "failure", null);
    }

    public static <T> CommonResult<T> failure(T data) {
        return new CommonResult<T>(500,"failure", data);
    }

    public static <T> CommonResult<T> failure(T data, String msg) {
        return new CommonResult<T>(500, msg, data);
    }

}
