package com.example.sqliteapplication.retrofithttp;


import com.example.sqliteapplication.mvp.VersionBean;
import com.example.sqliteapplication.retrofithttp.rxjava.BaseModel;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {

    @FormUrlEncoded
    @POST()
    Observable<BaseModel<VersionBean>> queryVersion(@Url String url, @Field("type") String type);

    @GET("police_alarm_service/jjApi/get_service_url")
    Observable<ResponseBody> getService(@Query("type") String type, @Query("version") String version);
}
