package com.yunche.finance.android.http;

import com.yunche.finance.android.http.base.BaseNetService;

/**
 * Created by littlexs on 2017/12/20.
 */

public class ApiService extends BaseNetService {

    private static final String BASE_URL = "http://q.qiqueqiao.com/";

    private static ApiService apiService;

    static {
        if (apiService == null) {
            synchronized (ApiService.class) {
                if (apiService == null) {
                    apiService = new ApiService();
                }
            }
        }
    }

    public static MyApi myApi() {
        return baseRetrofit(BASE_URL).create(MyApi.class);
    }

}
