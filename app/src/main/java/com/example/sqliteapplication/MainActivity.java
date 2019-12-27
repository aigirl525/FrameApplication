package com.example.sqliteapplication;

import android.os.Bundle;

import com.example.sqliteapplication.mvp.p.LoginPresenterImpl;
import com.example.sqliteapplication.mvp.v.base.BaseMvpActivity;
import com.example.sqliteapplication.mvp.p.LoginPresenter;
import com.example.sqliteapplication.mvp.v.LoginView;

public class MainActivity extends BaseMvpActivity<LoginView, LoginPresenter<LoginView>> implements LoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public LoginPresenter<LoginView> initPresenter() {
        return new LoginPresenterImpl();
    }

    @Override
    public LoginPresenter<LoginView> initActivity(Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void showLoginDialog() {

    }
}
