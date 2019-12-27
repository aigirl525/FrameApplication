package com.example.sqliteapplication;

public interface IBaseDao<T> {
    //插入数据
    Long insert(T t);
    Long update(T entity , T where);
}
