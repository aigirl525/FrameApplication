package com.example.sqliteapplication.greendao;

import android.app.Application;

import com.example.sqliteapplication.BuildConfig;

import java.io.File;


public class MyApplication extends Application {
    private static String sqliteDataBasePath;
    private static String sqliteDataBase;

    @Override
    public void onCreate() {
        super.onCreate();

        //初始化数据库路径
        if (android.os.Build.VERSION.SDK_INT >= 4.2) {
            sqliteDataBase = getApplicationContext().getApplicationInfo().dataDir + "/databases";
        } else {
            sqliteDataBase = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases";
        }
        File file = new File(sqliteDataBase);
        if (!file.exists()) {
            file.mkdir();
        }
        sqliteDataBasePath = sqliteDataBase + "/lwj.db";
        //initGreenDao();
    }

    private void initGreenDao() {
        DaoManager mManager = DaoManager.getInstance();
        mManager.init(this);
    }

    public static String getSqliteDataBasePath() {
        return sqliteDataBasePath;
    }

    public void setSqliteDataBasePath(String sqliteDataBasePath) {
        this.sqliteDataBasePath = sqliteDataBasePath;
    }


}
