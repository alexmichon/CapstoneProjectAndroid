package edu.berkeley.capstoneproject.capstoneprojectandroid.models.users;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.security.InvalidKeyException;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.network.requests.ApiRequest;

/**
 * Created by Alex on 05/11/2017.
 */

public class User {

    private static final String TAG = User.class.getSimpleName();

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("password")
    @Expose
    private String mPassword;

    @SerializedName("password_confirmation")
    @Expose
    private String mPasswordConfirmation;

    @SerializedName("first_name")
    @Expose
    private String mFirstName;

    @SerializedName("last_name")
    @Expose
    private String mLastName;

    private AuthHeaders mAuthHeaders;
    private boolean mAuthenticated = false;

    public User(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public User(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        mEmail = email;
        mPassword = password;
        mPasswordConfirmation = passwordConfirmation;
        mFirstName = firstName;
        mLastName = lastName;
    }


    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getPasswordConfirmation() {
        return mPasswordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        mPasswordConfirmation = passwordConfirmation;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public boolean isAuthenticated() {
        return mAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        mAuthenticated = authenticated;
    }


    public void authenticate(ApiRequest request) throws AuthFailureError, InvalidKeyException {
        mAuthHeaders = new AuthHeaders(request);
        setAuthenticated(true);
    }


    public static class AuthHeaders {

        private static final String KEY_ACCESS_TOKEN = "access-token";
        private static final String KEY_TOKEN_TYPE = "token-type";
        private static final String KEY_CLIENT = "client";
        private static final String KEY_EXPIRY = "expiry";
        private static final String KEY_UID = "uid";


        private String mAccessToken;
        private String mTokenType;
        private String mClient;
        private String mExpiry;
        private String mUid;

        public AuthHeaders(ApiRequest request) throws AuthFailureError, InvalidKeyException {
            Map<String, String> responseHeaders = request.getResponseHeaders();
            if (
                    !responseHeaders.containsKey(KEY_ACCESS_TOKEN) ||
                    !responseHeaders.containsKey(KEY_CLIENT) ||
                    !responseHeaders.containsKey(KEY_EXPIRY) ||
                    !responseHeaders.containsKey(KEY_TOKEN_TYPE) ||
                    !responseHeaders.containsKey(KEY_UID)
            ) {
                throw new InvalidKeyException("Authentication Headers not found");
            }

            mAccessToken    = (String) responseHeaders.get(KEY_ACCESS_TOKEN);
            mClient         = (String) responseHeaders.get(KEY_CLIENT);
            mExpiry         = (String) responseHeaders.get(KEY_EXPIRY);
            mTokenType      = (String) responseHeaders.get(KEY_TOKEN_TYPE);
            mUid            = (String) responseHeaders.get(KEY_UID);
        }
    }
}
