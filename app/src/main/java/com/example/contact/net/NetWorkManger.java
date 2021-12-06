package com.example.contact.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: YH
 * @date: 2021/12/2
 * @desc: retrofit封装
 */
public class NetWorkManger {

    private static final int DEFAULT_TIME_OUT = 5;
    private static final int DEFAULT_READ_TIME_OUT = 10;
    private final Retrofit mRetrofit;

    public static NetWorkManger getInstance() {
        return InnerClass.sInstance;
    }

    private static class InnerClass {
        private static final NetWorkManger sInstance = new NetWorkManger();
    }

    private NetWorkManger() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);

        HttpLoggingInterceptor loggingInterceptor =
                new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC);
        builder.addInterceptor(loggingInterceptor);//添加拦截日志

        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiConfig.BASE_URL)
                .build();
    }

    public <T> T create(Class<T> service) {
        return mRetrofit.create(service);
    }

}
