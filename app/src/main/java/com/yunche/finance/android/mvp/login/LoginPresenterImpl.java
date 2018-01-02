package com.yunche.finance.android.mvp.login;

/**
 * Created by littlexs on 2017/12/20.
 */

public class LoginPresenterImpl implements LoginConstract.LoginPresenter,LoginConstract.LoginInteractor.LoginBackListener {

    private LoginConstract.LoginView loginView;
    private LoginInteractorImpl loginInteractor;

    public LoginPresenterImpl(LoginConstract.LoginView loginView,LoginInteractorImpl loginInteractor){
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void login(String name, String passWord) {
        if (loginView!=null){
            loginView.showLoading();
        }
        loginInteractor.login(name,passWord,this,loginView.bindLifecycle());
    }

    @Override
    public void loginSuccess() {
        if (loginView!=null){
            loginView.loginSuccess();
        }
    }

    @Override
    public void loginError(String message) {
        if (loginView!=null){
            loginView.loginError(message);
        }
    }
}
