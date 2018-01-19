package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IAuthInterceptor;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Alex on 24/12/2017.
 */

@Singleton
public class AuthInterceptor implements Interceptor, IAuthInterceptor {

    private static final String KEY_ACCESS_TOKEN = "access-token";
    private static final String KEY_CLIENT = "client";
    private static final String KEY_EXPIRY = "expiry";
    private static final String KEY_TOKEN_TYPE = "token-type";
    private static final String KEY_UID = "uid";

    private Authentication mAuthentication;
    private Listener mListener;

    @Inject
    public AuthInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request();
        if (mAuthentication != null) {
             newRequest = chain.request().newBuilder()
                    .addHeader(KEY_ACCESS_TOKEN, mAuthentication.getAccessToken())
                    .addHeader(KEY_CLIENT, mAuthentication.getClient())
                    .addHeader(KEY_EXPIRY, mAuthentication.getExpiry())
                    .addHeader(KEY_TOKEN_TYPE, mAuthentication.getTokenType())
                    .addHeader(KEY_UID, mAuthentication.getUid())
                    .build();
        }

        Response response = chain.proceed(newRequest);

        String accessToken = response.header(KEY_ACCESS_TOKEN);
        String client = response.header(KEY_CLIENT);
        String expiry = response.header(KEY_EXPIRY);
        String tokenType = response.header(KEY_TOKEN_TYPE);
        String uid = response.header(KEY_UID);

        if ((accessToken != null) &&
                (client != null) &&
                (expiry != null) &&
                (tokenType != null) &&
                (uid != null)) {

            if (mAuthentication == null) {
                mAuthentication = new Authentication(accessToken, client, expiry, tokenType, uid);
            }
            else {
                mAuthentication.setAccessToken(accessToken);
                mAuthentication.setClient(client);
                mAuthentication.setExpiry(expiry);
                mAuthentication.setTokenType(tokenType);
                mAuthentication.setUid(uid);
            }

            if (mListener != null) {
                mListener.onAuthUpdate(mAuthentication);
            }
        }

        return response;
    }


    @Override
    public Authentication getAuthentication() {
        return mAuthentication;
    }

    @Override
    public void setAuthentication(Authentication auth) {
        mAuthentication = auth;
    }

    @Override
    public void setListener(Listener listener) {
        mListener = listener;
    }
}
