package com.gao.mall.tiny.malltiny01.common;

public class ResultCode implements ResponseCode{
    private int code;
    private String msg;

    public ResultCode(int code, String msg) {
        this.code = code;
        this.msg= msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
            return msg;
    }
}
