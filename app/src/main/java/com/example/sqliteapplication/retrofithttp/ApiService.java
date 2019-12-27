package com.example.sqliteapplication.retrofithttp;


import com.example.sqliteapplication.retrofithttp.rxjava.BaseModel;
import com.example.sqliteapplication.retrofithttp.rxjava.User;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
    /**
     * 登录
     */
    @POST("/login")
    Observable<BaseModel<User>> login(@Body RequestBody body);


    @FormUrlEncoded
    @POST("getUser")
    Observable<BaseModel<User>> getUser(@FieldMap Map<String, String> map);

    //https://gddev.highlight2018.com/police_alarm_service/jjApi/get_service_url?type=AND&version=1.0.1

    @GET()
    Observable<ResponseBody> login(@Url String url, @Query("username") String type, @Query("password") String version);
}
