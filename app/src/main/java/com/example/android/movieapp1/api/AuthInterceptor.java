package com.example.android.movieapp1.api;

import com.example.android.movieapp1.BuildConfig;
import com.example.android.movieapp1.config.AppConstants;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by invictus on 8/17/17.
 */

public class AuthInterceptor implements Interceptor {
    public AuthInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        HttpUrl httpUrl = request.url()
                .newBuilder()
                .addQueryParameter(AppConstants.API_KEY_ENTRY, BuildConfig.ACCOUNT_API_KEY)
                .build();

        request = request.newBuilder().url(httpUrl).build();
        return chain.proceed(request);
    }
}
