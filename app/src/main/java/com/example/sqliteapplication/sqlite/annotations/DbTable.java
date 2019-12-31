package com.example.sqliteapplication.sqlite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @author YTF
 * java 源文件 --》class --运行runtime
 * @Target 规定该注解要在哪里使用，这里是要在类中使用，所以使用TYPE
 * @Retention 规定该注解的存活时间，这里设置为运行时RUNTIME（因为建表操作是在程序运行时）
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
 public @interface DbTable{
     String value();
}
