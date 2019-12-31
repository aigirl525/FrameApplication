package com.example.sqliteapplication.sqlite;

import android.database.sqlite.SQLiteDatabase;

/**
 * 提供给调用层使用的对外接口
 */
public class BaseDaoFactory {
    private SQLiteDatabase sqLiteDatabase;
    private  String sqliteDataBasePath;
    private static  final BaseDaoFactory instance = new BaseDaoFactory();

    private  BaseDaoFactory(){
        //初始化数据库路径
        sqliteDataBasePath = "data/data/com.example.sqliteapplication/lwj.db";
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
