package com.example.sqliteapplication.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqliteapplication.BuildConfig;
import com.example.sqliteapplication.greendao.MyApplication;

import java.io.File;

/**
 * 提供给调用层使用的对外接口
 */
public class BaseDaoFactory {
    private SQLiteDatabase sqLiteDatabase;
    private static  final BaseDaoFactory instance = new BaseDaoFactory();

    private BaseDaoFactory(){
        String sqliteDataBase = "/data/data/" + BuildConfig.APPLICATION_ID + "/databases";
        File file = new File(sqliteDataBase);
        if (!file.exists()) {
            file.mkdir();
        }
        String sqliteDataBasePath = sqliteDataBase + "/lwj.db" ;

       sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(sqliteDataBasePath,null);
    }

    public static  BaseDaoFactory getInstance() {
        return instance;
    }

    public <T> BaseDao<T> getBaseDao(Class<T> entityCLass){
        BaseDao baseDao = null;
        try {
           baseDao = BaseDao.class.newInstance();
        }catch (InstantiationException e){
            e.printStackTrace();
        }catch (IllegalAccessException e){
            e.printStackTrace();
        }
        baseDao.init(sqLiteDatabase,entityCLass);
        return baseDao;
    }

}
