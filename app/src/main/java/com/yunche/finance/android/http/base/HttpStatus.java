package com.yunche.finance.android.http.base;

public class HttpStatus {

    //字段名称要和后台返回保持一致
    private int code;
    private String msg;

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

    /**
     * API是否请求失败
     * @return 失败返回true, 成功返回false
     */
    public boolean isCodeInvalid() {
        return code != HttpStatusCode.NET_OK;
    }
}