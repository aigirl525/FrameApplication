package com.example.sqliteapplication.mvp.presenter;

import android.app.Dialog;

import com.example.sqliteapplication.mvp.presenter.base.BasePresenter;

public interface LoginPresenter<V> extends BasePresenter<V> {
    void queryVersion(Dialog dialog, String type);
    void getService(Dialog dialog, String type , String version);
}
