package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
public class AuthInterceptor implements Interceptor {

    private static final String KEY_ACCESS_TOKEN = "access-token";
    private static final String KEY_CLIENT = "client";
    private static final String KEY_EXPIRY = "expiry";
    private static final String KEY_TOKEN_TYPE = "token-type";
    private static final String KEY_UID = "uid";

    private String mAccessToken;
    private String mClient;
    private String mExpiry;
    private String mTokenType;
    private String mUid;

    @Inject
    public AuthInterceptor() {
        mAccessToken = "";
        mClient = "";
        mExpiry = "";
        mTokenType = "";
        mUid = "";
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .addHeader(KEY_ACCESS_TOKEN, mAccessToken)
                .addHeader(KEY_CLIENT, mClient)
                .addHeader(KEY_EXPIRY, mExpiry)
                .addHeader(KEY_TOKEN_TYPE, mTokenType)
                .addHeader(KEY_UID, mUid)
                .build();

        Response response = chain.proceed(newRequest);

        String accessToken = response.header(KEY_ACCESS_TOKEN);
        if (accessToken != null) {
            mAccessToken = accessToken;
        }

        String client = response.header(KEY_CLIENT);
        if (client != null) {
            mClient = client;
        }

        String expiry = response.header(KEY_EXPIRY);
        if (expiry != null) {
            mExpiry = expiry;
        }

        String tokenType = response.header(KEY_TOKEN_TYPE);
        if (tokenType != null) {
            mTokenType = tokenType;
        }

        String uid = response.header(KEY_UID);
        if (uid != null) {
            mUid = uid;
        }

        return response;
    }

}
