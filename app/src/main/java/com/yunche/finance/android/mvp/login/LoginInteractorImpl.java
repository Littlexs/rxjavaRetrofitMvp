package com.yunche.finance.android.mvp.login;

import android.content.Context;
import android.text.TextUtils;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yunche.finance.android.http.ApiService;
import com.yunche.finance.android.http.base.BaseObserver;
import com.yunche.finance.android.http.base.ResultBody;
import com.yunche.finance.android.http.base.RxSchedulers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


/**
 * Created by littlexs on 2017/12/20.
 */

public class LoginInteractorImpl implements LoginConstract.LoginInteractor {

    private Context mContext;
    private LifecycleTransformer<ResultBody<String>> transformer;

    public LoginInteractorImpl(Context mContext,LifecycleTransformer<ResultBody<String>> transformer) {
        this.mContext = mContext;
        this.transformer = transformer;
    }

    @Override
    public void login(final String name, final String passWord, final LoginBackListener loginBackListener) {
        if (TextUtils.isEmpty(name)) {
            loginBackListener.loginError("用户名为空");
            return;
        }
        if (TextUtils.isEmpty(passWord)) {
            loginBackListener.loginError("密码为空");
            return;
        }
        //网络请求的封装 ----  test
        ApiService.myApi().getMainInfo()
                .compose(RxSchedulers.compose(transformer))
                .subscribe(new BaseObserver<String>(mContext) {
                    @Override
                    protected void onHandleSuccess(String s) {
                        loginBackListener.loginSuccess();
                    }
                    @Override
                    protected void onHandleError(int code, String msg) {
                        super.onHandleError(code, msg);
                        loginBackListener.loginError(msg);
                    }
                });
    }
}
