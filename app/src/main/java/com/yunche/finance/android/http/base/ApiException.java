package com.yunche.finance.android.http.base;

public class ApiException extends RuntimeException {
    private int mErrorCode;

    public ApiException(int errorCode, String errorMessage) {
        super(errorMessage);
        mErrorCode = errorCode;
    }

    /**
     * 判断是否是token失效 假设为
     * @return 失效返回true, 否则返回false;
     */
    public boolean isTokenExpried() {
        return mErrorCode == HttpStatusCode.NET_TOKEN_EXPRIED;
    }

    public int getCode(){return mErrorCode;}
}