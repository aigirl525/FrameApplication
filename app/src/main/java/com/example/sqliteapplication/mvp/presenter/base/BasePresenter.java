package com.example.sqliteapplication.mvp.presenter.base;

public interface BasePresenter<V> {
    //绑定view，这个方法将会在activity中调用
    void attach(V mView);
    //解绑
    void dettach();
    //也就是activity的onResume(),presenter一般在这个方法里面做一些监听事件
    void onResume();

    V obtainView();

    boolean isAttach();
}
