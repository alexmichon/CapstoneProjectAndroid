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
            mAdminAuth = new AuthenticationBuilder()
                    .withAccessToken("123456789")
                    .withClient("client")
                    .withExpiry("123456789")
                    .withTokenType("token")
                    .withUid("admin@admin.com")
                    .build();
        }
        return mAdminAuth;
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
