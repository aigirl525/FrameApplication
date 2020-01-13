package com.example.sqliteapplication.sqlite.entity;

import com.example.sqliteapplication.sqlite.annotations.DbField;
import com.example.sqliteapplication.sqlite.annotations.DbTable;

/**
 * 对是实体类使用自定义注解，通过反射拿到实体类中的变量
 * 将变量自动创建到表中作为表的字段名
 */
@DbTable("tb_user")//表名
public class User {
    @DbField("_id")//字段名 主键必须是_id，也可以不设置
    private Integer id;
    @DbField("name")
    private String name;
    @DbField("pwd")
    private String password;

    public User() {
    }
    public User(Integer id) {
        this.id = id;
    }

    public User(Integer id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
