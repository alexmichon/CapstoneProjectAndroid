package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

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

    private final Map<String, String> mMap = new HashMap<>();

    @Inject
    public ApiHeader() {
        mMap.put(KEY_ACCEPT, "application/json");
        mMap.put(KEY_CONTENT_TYPE, "application/json");
    }

    public String getAccessToken() {
        return mMap.get(KEY_ACCESS_TOKEN);
    }

    public void setAccessToken(String accessToken) {
        mMap.put(KEY_ACCESS_TOKEN, accessToken);
    }

    public String getClient() {
        return mMap.get(KEY_CLIENT);
    }

    public void setClient(String client) {
        mMap.put(KEY_CLIENT, client);
    }

    public String getExpiry() {
        return mMap.get(KEY_EXPIRY);
    }

    public void setExpiry(String expiry) {
        mMap.put(KEY_EXPIRY, expiry);
    }

    public String getTokenType() {
        return mMap.get(KEY_TOKEN_TYPE);
    }

    public void setTokenType(String tokenType) {
        mMap.put(KEY_TOKEN_TYPE, tokenType);
    }

    public String getUid() {
        return mMap.get(KEY_UID);
    }

    public void setUid(String uid) {
        mMap.put(KEY_UID, uid);
    }

    public Map<String, String> getMap() {
        return mMap;
    }

    public void addHeader(String key, String value) {
        mMap.put(key, value);
    }

    public String getHeader(String key) {
        return mMap.get(key);
    }
}
