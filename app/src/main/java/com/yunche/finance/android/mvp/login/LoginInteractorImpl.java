package com.yunche.finance.android.mvp.login;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yunche.finance.android.http.ApiService;
import com.yunche.finance.android.http.base.BaseObserver;
import com.yunche.finance.android.http.base.RxSchedulers;


/**
 * Created by littlexs on 2017/12/20.
 */

public class LoginInteractorImpl implements LoginConstract.LoginInteractor  {

    private Context mContext;

    public LoginInteractorImpl(Context mContext) {
        this.mContext = mContext;

    }

    @SuppressWarnings("unchecked")
    @Override
    public void login(final String name, final String passWord,
                      final LoginBackListener loginBackListener,LifecycleTransformer transformer) {
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
                        Log.i("---","onHandleSuccess");
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
