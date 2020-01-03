package com.example.sqliteapplication.greendao;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }
    private void initGreenDao(){
        DaoManager mManager = DaoManager.getInstance();
        mManager.init(this);
    }
}
