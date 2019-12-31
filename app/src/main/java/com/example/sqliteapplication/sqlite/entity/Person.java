package com.example.sqliteapplication.sqlite.entity;

import com.example.sqliteapplication.sqlite.annotations.DbField;
import com.example.sqliteapplication.sqlite.annotations.DbTable;

@DbTable("tb_person")//表名
public class Person {
    @DbField("age")
    private Integer age;
    @DbField("name")
    private String name;

    public Person(Integer id, String name) {
        this.age = id;
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
