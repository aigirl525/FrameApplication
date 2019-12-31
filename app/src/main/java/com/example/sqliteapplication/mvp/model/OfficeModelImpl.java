package com.example.sqliteapplication.mvp.model;


import com.example.sqliteapplication.retrofithttp.RetroFactory;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OfficeModelImpl implements OfficeModel {

    @Override
    public Observable queryVersion(String url , String type) {
        return RetroFactory.getInstance().queryVersion(url,type);
    }

    @Override
    public Observable getService(String type, String version) {
        return RetroFactory.getInstance().getService(type,version);
    }


}
