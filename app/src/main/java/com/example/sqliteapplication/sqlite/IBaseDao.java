package com.example.sqliteapplication.sqlite;

/**
 * 数据库顶层接口
 * @param <T>
 */
public interface IBaseDao<T> {
    //插入数据 我们这里要实现的就是可以直接插入一个实体类就可以自动在指定位置创建对应的数据库表文件
    Long insert(T t);
    Long update(T entity , T where);
}
