package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user;

/**
 * Created by Alex on 14/12/2017.
 */

public class AuthenticationFactory {

    private static Authentication mAdminAuth;

    public static Builder builder() {
        return new Builder();
    }

    public static Authentication admin() {
        if (mAdminAuth == null) {
            mAdminAuth = builder()
                    .withUid("admin@admin.com")
                    .build();
        }
        return mAdminAuth;
    }

    public static Authentication create() {
        return new Builder()
                .withAccessToken("access-token")
                .withClient("client")
                .withExpiry("expiry")
                .withTokenType("token-type")
                .withUid("uid")
                .build();
    }


    public static class Builder {

        private String mAccessToken;
        private String mClient;
        private String mExpiry;
        private String mTokenType;
        private String mUid;

        public Builder() {

        }

        public Builder withAccessToken(String accessToken) {
            mAccessToken = accessToken;
            return this;
        }

        public Builder withClient(String client) {
            mClient = client;
            return this;
        }

        public Builder withExpiry(String expiry) {
            mExpiry = expiry;
            return this;
        }

        public Builder withTokenType(String tokenType) {
            mTokenType = tokenType;
            return this;
        }

        public Builder withUid(String uid) {
            mUid = uid;
            return this;
        }

        public Authentication build() {
            return new Authentication(mAccessToken, mClient, mExpiry, mTokenType, mUid);
        }
    }
}
