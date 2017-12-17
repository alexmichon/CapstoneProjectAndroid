package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alex on 14/12/2017.
 */

public class Authentication {

    private String mAccessToken;
    private String mClient;
    private String mExpiry;
    private String mTokenType;
    private String mUid;

    public Authentication(String accessToken, String client, String expiry, String tokenType, String uid) {
        mAccessToken = accessToken;
        mClient = client;
        mExpiry = expiry;
        mTokenType = tokenType;
        mUid = uid;
    }

    public Authentication(Map<String, String> headers) {
        mAccessToken = headers.get("access-token");
        mClient = headers.get("client");
        mExpiry = headers.get("expiry");
        mTokenType = headers.get("token-type");
        mUid = headers.get("uid");
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String accessToken) {
        mAccessToken = accessToken;
    }

    public String getClient() {
        return mClient;
    }

    public void setClient(String client) {
        mClient = client;
    }

    public String getExpiry() {
        return mExpiry;
    }

    public void setExpiry(String expiry) {
        mExpiry = expiry;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public void setTokenType(String tokenType) {
        mTokenType = tokenType;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String uid) {
        mUid = uid;
    }



    public boolean isValid() {
        if ((mAccessToken == null) ||
                (mClient == null) ||
                (mExpiry == null) ||
                (mTokenType == null) ||
                (mUid == null)){
            return false;
        }

        long expiry = Long.valueOf(mExpiry);
        return new Date(expiry).after(new Date());
    }



    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>(5);
        map.put("access-token", mAccessToken);
        map.put("client", mClient);
        map.put("expiry", mExpiry);
        map.put("token-type", mTokenType);
        map.put("uid", mUid);

        return map;
    }
}
