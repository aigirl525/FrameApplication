package com.example.sqliteapplication.mvp;

import android.os.Bundle;
import android.util.Log;

import com.example.sqliteapplication.R;
import com.example.sqliteapplication.mvp.presenter.LoginPresenter;
import com.example.sqliteapplication.mvp.presenter.LoginPresenterImpl;
import com.example.sqliteapplication.mvp.view.LoginView;
import com.example.sqliteapplication.mvp.view.base.BaseMvpActivity;

public class LoginActivity extends BaseMvpActivity<LoginView, LoginPresenter<LoginView>> implements LoginView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter.getService("AND","1.0.1");
    }

    @Override
    public void showLoginDialog() {

    }

    @Override
    public void showGetServiceSucess() {
        Log.e("123456","showGetServiceSucess");
    }


    @Override
    public LoginPresenter initPresenter() {
        return new LoginPresenterImpl(this);
    }

    @Override
    public void initActivity(Bundle savedInstanceState) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
