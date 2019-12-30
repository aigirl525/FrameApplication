package com.example.sqliteapplication.mvp.model;


import io.reactivex.Observable;

public interface OfficeModel  {
    Observable login(String username, String password);
    Observable getService(String type , String version);
}
