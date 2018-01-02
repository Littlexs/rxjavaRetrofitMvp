package com.yunche.finance.android.mvp.login;


import android.support.annotation.NonNull;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.yunche.finance.android.http.base.ResultBody;

/**
 * Created by littlexs on 2017/12/20.
 */

public interface LoginConstract {
    interface LoginPresenter{
        void login(String name, String passWord);
    }

    interface LoginView{
        void showLoading();
        void loginError(String message);
        void loginSuccess();
        LifecycleTransformer bindLifecycle();
    }

    //耦合因子，解耦presenter数据层
    interface LoginInteractor{
        interface LoginBackListener{
            void loginSuccess();
            void loginError(String message);
        }
        void login(String name, String passWord, LoginBackListener loginBackListener,LifecycleTransformer transformer);
    }
}
