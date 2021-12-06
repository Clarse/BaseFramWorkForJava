package com.example.contact.net;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author: YH
 * @date: 2021/12/2
 * @desc: 公共拦截器
 */
public class HttpCommonInterceptor implements Interceptor {

    //利用集合Map来装我们Header
    private final Map<String, String> mHeaderParamsMap = new HashMap<>();

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.d("httpCommonInterceptor", "add common params");
        Request oldRequest = chain.request();
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params :
                    mHeaderParamsMap.entrySet()) {
                requestBuilder.addHeader(params.getKey(), params.getValue());
            }
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {
        HttpCommonInterceptor mHttpCommonInterceptor;

        public Builder() {
            mHttpCommonInterceptor = new HttpCommonInterceptor();
        }

        public Builder addHeaderParams(String key, String value) {
            mHttpCommonInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public HttpCommonInterceptor build() {
            return mHttpCommonInterceptor;
        }
    }

}
