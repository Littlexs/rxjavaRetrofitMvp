package com.yunche.finance.android.http.base;

/**
 * Created by littlexs on 2017/12/20.
 */

public class HttpStatusCode {
    public static int NET_OK = 200;//请求成功
    public static int NET_TOKEN_EXPRIED = 0;//token过期，根据后台返回修改
    public static int NET_PUSHED = 1;//账号在别的设备登录
}
