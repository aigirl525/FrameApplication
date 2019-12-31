package com.example.sqliteapplication.mvp.model;


import io.reactivex.Observable;

public interface OfficeModel  {
    Observable queryVersion(String url , String type);
    Observable getService(String type , String version);
}
