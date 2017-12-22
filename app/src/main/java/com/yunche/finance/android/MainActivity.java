package com.yunche.finance.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yunche.finance.android.http.ApiService;
import com.yunche.finance.android.http.base.ResultBody;
import com.yunche.finance.android.mvp.login.LoginActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toLogin(View view) {
        openActivity(LoginActivity.class);
    }


    public void openActivity(Class clazz){
        startActivity(new Intent(MainActivity.this,clazz));
    }
}
