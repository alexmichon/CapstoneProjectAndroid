package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;

/**
 * Created by Alex on 20/11/2017.
 */

public class ApiHeader {

    public static final String KEY_ACCESS_TOKEN = "access-token";
    public static final String KEY_CLIENT = "client";
    public static final String KEY_EXPIRY = "expiry";
    public static final String KEY_TOKEN_TYPE = "token-type";
    public static final String KEY_UID = "uid";

    public static final String KEY_ACCEPT = "Accept";
    public static final String KEY_CONTENT_TYPE = "Content-Type";

    private final Map<String, String> mHeaders = new HashMap<>();

    @Inject
    public ApiHeader() {
        mHeaders.put(KEY_ACCEPT, "application/json");
        mHeaders.put(KEY_CONTENT_TYPE, "application/json");
    }

    public String getAccessToken() {
        return getHeader(KEY_ACCESS_TOKEN);
    }

    public void setAccessToken(String accessToken) {
        addHeader(KEY_ACCESS_TOKEN, accessToken);
    }

    public String getClient() {
        return getHeader(KEY_CLIENT);
    }

    public void setClient(String client) {
        addHeader(KEY_CLIENT, client);
    }

    public String getExpiry() {
        return getHeader(KEY_EXPIRY);
    }

    public void setExpiry(String expiry) {
        addHeader(KEY_EXPIRY, expiry);
    }

    public String getTokenType() {
        return getHeader(KEY_TOKEN_TYPE);
    }

    public void setTokenType(String tokenType) {
        addHeader(KEY_TOKEN_TYPE, tokenType);
    }

    public String getUid() {
        return getHeader(KEY_UID);
    }

    public void setUid(String uid) {
        addHeader(KEY_UID, uid);
    }

    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public void addHeader(String key, String value) {
        mHeaders.put(key, value);
    }

    public String getHeader(String key) {
        return mHeaders.get(key);
    }

    public Authentication getAuthentication() {
        return new Authentication(
                getAccessToken(),
                getClient(),
                getExpiry(),
                getTokenType(),
                getUid()
        );
    }

    public void setAuthentication(Authentication authentication) {
        setAccessToken(authentication.getAccessToken());
        setClient(authentication.getClient());
        setExpiry(authentication.getExpiry());
        setTokenType(authentication.getTokenType());
        setUid(authentication.getUid());
    }
}
