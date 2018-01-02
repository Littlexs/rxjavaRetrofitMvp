package com.yunche.finance.android.http.base;


import com.yunche.finance.android.http.converter.FastJsonConverterFactory;
import com.yunche.finance.android.http.interceptors.PubicParamsInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class BaseNetService {

    private static OkHttpClient client;
    private static Retrofit retrofit;

    static {
        if (client == null) {
            synchronized (BaseNetService.class) {
                if (client == null) {
                    client = new OkHttpClient.Builder()
                            .addInterceptor(new PubicParamsInterceptor())//用于添加公共参数
                            .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))//拦截器，用于日志的打印
                            .build();
                }
            }
        }
    }

    protected static Retrofit baseRetrofit(String baseUrl) {
        if (retrofit == null) {
            synchronized (BaseNetService.class) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .client(client)
                            .baseUrl(baseUrl)
                            .addConverterFactory(FastJsonConverterFactory.create())//默认直接转化为实体类，不会进行解密等处理，如需加解密请自定义转换器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Retrofit2与Rxjava2之间结合的适配器
                            .build();
                }
            }
        }
        return retrofit;
    }
}