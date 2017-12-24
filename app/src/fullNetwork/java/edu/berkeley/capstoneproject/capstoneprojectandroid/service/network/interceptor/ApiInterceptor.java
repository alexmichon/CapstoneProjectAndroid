package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.interceptor;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alex on 24/12/2017.
 */

@Singleton
public class ApiInterceptor implements Interceptor {

    @Inject
    public ApiInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .build();

        return chain.proceed(newRequest);
    }
}
