package com.example.sqliteapplication.mvp.model;


import com.example.sqliteapplication.retrofithttp.RetroFactory;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class OfficeModelImpl implements OfficeModel {

    @Override
    public Observable login(String username, String password) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String json = jsonObject.toString();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        return RetroFactory.getInstance().login(requestBody);
    }

    @Override
    public Observable getService(String type, String version) {
        return RetroFactory.getInstance().getService(type,version);
    }


}
