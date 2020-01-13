package com.example.sqliteapplication.sqlite;

import java.util.List;

/**
 * 数据库顶层接口
 * @param <T>
 */
public interface IBaseDao<T> {
    //插入数据 我们这里要实现的就是可以直接插入一个实体类就可以自动在指定位置创建对应的数据库表文件
    Long insert(T t);


    /**
     * 更新
     * @param entity
     * @param where
     * @return
     */
     int update(T entity ,T where) ;

    /**
     * 删除
     * @param where
     * @return
     */
     int delete(T where);

    /**
     * 查询
     * @param where
     * @return
     */
     List<T> query(T where) ;
     List<T> query(T where,String orderBy,Integer startIndex,Integer limit) ;

    /**
     * 关闭
     */
    void close();


}
