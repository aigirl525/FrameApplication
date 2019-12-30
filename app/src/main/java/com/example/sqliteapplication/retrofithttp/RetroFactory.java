package com.example.sqliteapplication.retrofithttp;


import com.example.sqliteapplication.BuildConfig;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.Wang on 2017/9/27.
 * 909732184@qq.com
 */

public class RetroFactory {
    private static Request request = null;

    private RetroFactory() {
    }

    private static OkHttpClient httpClient;

    private static ApiService retrofitService;

    public static ApiService getInstance() {
        String baseUrl = "https://gddev.highlight2018.com/police_alarm_service/";
        if (retrofitService == null) {
            synchronized (RetroFactory.class) {
                request = new Request.Builder()
                        .url(baseUrl)
                        .build();

                if (httpClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(30, TimeUnit.SECONDS);

                    if (BuildConfig.DEBUG) {
                        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                        builder.addInterceptor(loggingInterceptor);
                    }
                    httpClient = builder.build();
                }

                httpClient.newCall(request);
                retrofitService = new Retrofit.Builder()
                         .baseUrl(baseUrl)
                        //Retrofit与FastJson混用FastJsonConverterFactory
                        //Retrofit与Gson混用GsonConverterFactory
                        .addConverterFactory(GsonConverterFactory.create(new Gson()))
                        //rx与retrofit混用 将retrofit原本返回的call转换为Observable
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(httpClient)
                        .build()
                        .create(ApiService.class);
            }
        }
        return retrofitService;
    }
}
