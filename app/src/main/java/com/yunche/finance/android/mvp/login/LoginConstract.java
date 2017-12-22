package com.yunche.finance.android.mvp.login;

/**
 * Created by littlexs on 2017/12/20.
 */

public interface LoginConstract {
    interface LoginPresenter{
        void login(String name,String passWord);
    }

    interface LoginView{
        void showLoading();
        void loginError(String message);
        void loginSuccess();
    }

    //耦合因子，解耦presenter数据层
    interface LoginInteractor{
        interface LoginBackListener{
            void loginSuccess();
            void loginError(String message);
        }
        void login(String name,String passWord ,LoginBackListener loginBackListener);
    }
}
