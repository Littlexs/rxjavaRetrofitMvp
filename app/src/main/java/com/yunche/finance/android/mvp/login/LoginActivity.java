package com.yunche.finance.android.mvp.login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yunche.finance.android.R;
import com.yunche.finance.android.http.base.ResultBody;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends RxAppCompatActivity implements LoginConstract.LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private AutoCompleteTextView etName;
    private EditText etPassWord;
    private Button button;

    private LoginConstract.LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.login_progress);
        etName = findViewById(R.id.name);
        etPassWord = findViewById(R.id.password);
        button = findViewById(R.id.sign_in);

        loginPresenter = new LoginPresenterImpl(this,
                new LoginInteractorImpl(getApplicationContext(), this.<ResultBody<String>>bindUntilEvent(ActivityEvent.PAUSE)));

        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        loginPresenter.login(etName.getText().toString(),etPassWord.getText().toString());
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void loginSuccess() {
        progressBar.setVisibility(View.GONE);
        toast("登陆成功");
    }

    @Override
    public void loginError(String message) {
        progressBar.setVisibility(View.GONE);
        toast(message);
    }


    public void toast(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }

}

