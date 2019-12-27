package com.example.sqliteapplication;

public abstract class BaseDao<T> implements IBaseDao<T> {


    @Override
    public Long insert(T t) {
        return null;
    }

    @Override
    public Long update(T entity, T where) {
        return null;
    }
}
