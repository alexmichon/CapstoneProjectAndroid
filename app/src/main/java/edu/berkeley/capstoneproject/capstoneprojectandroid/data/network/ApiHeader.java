package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import javax.inject.Inject;

/**
 * Created by Alex on 20/11/2017.
 */

public class ApiHeader {

    private String mAccessToken;
    private String mClient;
    private String mExpiry;
    private String mTokenType;
    private String mUid;

    @Inject
    public ApiHeader() {

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


    public Builder rebuild() {
        return new Builder(this);
    }

    public class Builder {

        private ApiHeader mApiHeader;

        private String mAccessToken;
        private String mClient;
        private String mExpiry;
        private String mTokenType;
        private String mUid;

        public Builder() {

        }

        public Builder(ApiHeader apiHeader) {
            mApiHeader = apiHeader;
            mAccessToken = apiHeader.getAccessToken();
            mClient = apiHeader.getClient();
            mExpiry = apiHeader.getExpiry();
            mTokenType = apiHeader.getTokenType();
            mUid = apiHeader.getUid();
        }

        public Builder accessToken(String accessToken) {
            mAccessToken = accessToken;
            return this;
        }

        public Builder client(String client) {
            mClient = client;
            return this;
        }

        public Builder expiry(String expiry) {
            mExpiry = expiry;
            return this;
        }

        public Builder tokenType(String tokenType) {
            mTokenType = tokenType;
            return this;
        }

        public Builder uid(String uid) {
            mUid = uid;
            return this;
        }

        public ApiHeader build() {
            if (mApiHeader == null) {
                mApiHeader = new ApiHeader();
            }

            mApiHeader.setAccessToken(mAccessToken);
            mApiHeader.setClient(mClient);
            mApiHeader.setExpiry(mExpiry);
            mApiHeader.setTokenType(mTokenType);
            mApiHeader.setUid(mUid);

            return mApiHeader;
        }

    }
}
