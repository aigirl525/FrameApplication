package com.example.sqliteapplication.mvp.presenter;

import com.example.sqliteapplication.mvp.presenter.base.BasePresenter;

public interface LoginPresenter<V> extends BasePresenter<V> {
    void login(String username,String password);
    void getService(String type , String version);
}
