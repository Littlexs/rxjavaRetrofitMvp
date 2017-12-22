package com.yunche.finance.android.http.base;

import android.content.Context;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;


public abstract class BaseObserver<T> implements Observer<ResultBody<T>> {

    private static final String TAG = "BaseObserver";
    private Context mContext;
    private final int RESPONSE_FATAL_EOR = -1;
    private int errorCode;
    private String errorMsg="未知的错误！";

    public BaseObserver(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onSubscribe(Disposable d) {
        //可以添加加载框的显示与监听

    }

    @Override
    public void onNext(ResultBody<T> value) {
        if (value.getCode()==200) {
            onHandleSuccess(value.getResult());
        } else {
            onHandleError(value.getCode(), value.getMessage());
        }
    }

    @Override
    public void onError(Throwable t) {
        if (t instanceof ApiException) {//自定义业务
            ApiException apiException = (ApiException) t;
            errorCode = apiException.getCode();
            errorMsg = apiException.getMessage();
        }else if (t instanceof HttpException) {//以下是其它状态判断
            HttpException httpException = (HttpException) t;
            errorCode = httpException.code();
            errorMsg = httpException.getMessage();
        } else if (t instanceof SocketTimeoutException) {  //VPN open
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "服务器响应超时";
        } else if (t instanceof ConnectException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "网络连接异常，请检查网络";
        }else if (t instanceof UnknownHostException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "无法解析主机，请检查网络连接";
        } else if (t instanceof UnknownServiceException) {
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "未知的服务器错误";
        } else if (t instanceof IOException) {  //飞行模式等
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "没有网络，请检查网络连接";
        } else if (t instanceof NetworkOnMainThreadException) {//主线程不能网络请求，这个很容易发现
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "主线程不能网络请求";
        } else if (t instanceof RuntimeException) { //很多的错误都是extends RuntimeException
            errorCode = RESPONSE_FATAL_EOR;
            errorMsg = "运行时错误,可能是返回数据格式与本地数据格式不匹配";
        }
        onHandleError(errorCode,errorMsg);
    }

    @Override
    public void onComplete() {
        Log.d(TAG, "onComplete");
    }


    protected abstract void onHandleSuccess(T t);

    protected void onHandleError(int code, String msg) {
        if (code != RESPONSE_FATAL_EOR && mContext != null) {
            disposeEorCode(msg,code);
        }
    }

    /**
     * 对通用问题的统一拦截处理,根据项目的特定的做法
     * 比如token过期，被迫下线等
     * @param code
     */
    private final void disposeEorCode(String message, int code) {
        switch (code) {
            case 101:
            case 112:
            case 123:
            case 401:
                break;
        }
        if (mContext != null&& Thread.currentThread().getName().toString().equals("main")) {
            toast(message + "   code=" + code);
        }

    }

    public void toast(String message) {
        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
    }

}