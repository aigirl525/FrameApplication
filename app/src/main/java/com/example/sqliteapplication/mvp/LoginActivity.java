package com.example.sqliteapplication.mvp;

import android.os.Bundle;

import com.example.sqliteapplication.R;
import com.example.sqliteapplication.mvp.p.LoginPresenter;
import com.example.sqliteapplication.mvp.p.LoginPresenterImpl;
import com.example.sqliteapplication.mvp.v.LoginView;
import com.example.sqliteapplication.mvp.v.base.BaseMvpActivity;

public class LoginActivity extends BaseMvpActivity<LoginView, LoginPresenter<LoginView>> implements LoginView {

    private  LoginPresenter loginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginPresenter.login("123","123");

    }

    @Override
    public void showLoginDialog() {

    }


    @Override
    public LoginPresenter<LoginView> initPresenter() {
        loginPresenter = new LoginPresenterImpl(this);
        loginPresenter.attach(this);
        return loginPresenter;
    }

    @Override
    public LoginPresenter<LoginView> initActivity(Bundle savedInstanceState) {
        return null;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loginPresenter != null){
            loginPresenter.dettach();
        }
    }
}
