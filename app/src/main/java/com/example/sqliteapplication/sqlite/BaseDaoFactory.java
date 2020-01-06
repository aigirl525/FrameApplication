package com.example.sqliteapplication.sqlite;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.sqliteapplication.greendao.MyApplication;

/**
 * 提供给调用层使用的对外接口
 */
public class BaseDaoFactory {
    private SQLiteDatabase sqLiteDatabase;
    private static  final BaseDaoFactory instance = new BaseDaoFactory();

    private  BaseDaoFactory(){
       // String sqliteDataBasePath="data/data/com.example.sqliteapplication/ytf.db";
        Log.e("getSqliteDataBasePath",MyApplication.getSqliteDataBasePath());
       sqLiteDatabase = SQLiteDatabase.openOrCreateDatabase(MyApplication.getSqliteDataBasePath(),null);
        //sqLiteDatabase = SQLiteDatabase.openDatabase(MyApplication.getSqliteDataBasePath(), null, SQLiteDatabase.OPEN_READWRITE);
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
