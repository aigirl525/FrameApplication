package com.example.sqliteapplication.mvp.p;

import com.example.sqliteapplication.mvp.p.base.BasePresenter;

public interface LoginPresenter<V> extends BasePresenter<V> {
    void login(String username,String password);
}
