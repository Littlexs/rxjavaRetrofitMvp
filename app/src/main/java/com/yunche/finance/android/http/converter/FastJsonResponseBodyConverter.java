package com.yunche.finance.android.http.converter;


import com.alibaba.fastjson.JSON;
import com.yunche.finance.android.http.base.ApiException;
import com.yunche.finance.android.http.base.HttpStatus;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit2.Converter;


final class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    /*
    * 转换方法
    */
    @Override
    public T convert(ResponseBody value) throws IOException {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        HttpStatus resultBody = JSON.parseObject(tempStr,HttpStatus.class);
        if (resultBody.isCodeInvalid()){
            throw new ApiException(resultBody.getCode(),resultBody.getMsg());
        }
        return JSON.parseObject(tempStr,type);

    }
}
