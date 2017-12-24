package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user;

/**
 * Created by Alex on 14/12/2017.
 */

public class AuthenticationFactory {

    private static Authentication mAdminAuth;

    public static AuthenticationBuilder builder() {
        return new AuthenticationBuilder();
    }

    public static Authentication admin() {
        if (mAdminAuth == null) {
            mAdminAuth = defaultBuilder()
                    .withUid("admin@admin.com")
                    .build();
        }
        return mAdminAuth;
    }

    public static AuthenticationBuilder defaultBuilder() {
        return new AuthenticationBuilder()
                .withAccessToken("access-token")
                .withClient("client")
                .withExpiry("expiry")
                .withTokenType("token-type")
                .withUid("uid");
    }


    public static class AuthenticationBuilder {

        private String mAccessToken;
        private String mClient;
        private String mExpiry;
        private String mTokenType;
        private String mUid;

        public AuthenticationBuilder() {

        }

        public AuthenticationBuilder withAccessToken(String accessToken) {
            mAccessToken = accessToken;
            return this;
        }

        public AuthenticationBuilder withClient(String client) {
            mClient = client;
            return this;
        }

        public AuthenticationBuilder withExpiry(String expiry) {
            mExpiry = expiry;
            return this;
        }

        public AuthenticationBuilder withTokenType(String tokenType) {
            mTokenType = tokenType;
            return this;
        }

        public AuthenticationBuilder withUid(String uid) {
            mUid = uid;
            return this;
        }

        public Authentication build() {
            return new Authentication(mAccessToken, mClient, mExpiry, mTokenType, mUid);
        }
    }
}
