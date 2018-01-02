package com.yunche.finance.android.http;


import com.yunche.finance.android.http.base.HttpStatus;
import com.yunche.finance.android.http.base.ResultBody;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by littlexs on 2017/12/20.
 */

public interface MyApi {

    @GET("api/v1/getHelp")
    Observable<ResultBody<String>> getMainInfo();

}
