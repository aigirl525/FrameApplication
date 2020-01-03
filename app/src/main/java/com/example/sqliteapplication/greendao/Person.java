package com.example.sqliteapplication.greendao;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.NotNull;

@Entity //表名
public class Person {
    @Id(autoincrement = true)
    private Long id;

    private String age;
    @NotNull
    private String name;
    @Generated(hash = 679523286)
    public Person(Long id, String age, @NotNull String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
    @Generated(hash = 1024547259)
    public Person() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getAge() {
        return this.age;
    }
    public void setAge(String age) {
        this.age = age;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }



}
