package com.example.sqliteapplication.mvp.m;


import io.reactivex.Observable;

public interface OfficeModel  {
    Observable login(String username, String password);
}
