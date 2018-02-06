package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

/**
 * Created by Alex on 10/11/2017.
 */

public class AuthenticationResponse extends BaseResponse<User> {

    @SerializedName("data")
    @Expose
    private UserResponse mUserResponse;

    private String mAccessToken;
    private String mClient;
    private String mExpiry;
    private String mTokenType;
    private String mUid;

    public void setHeaders(Map<String, List<String>> headers) {
        mAccessToken = headers.get("access-token").get(0);
        mClient      = headers.get("client").get(0);
        mExpiry      = headers.get("expiry").get(0);
        mTokenType   = headers.get("token-type").get(0);
        mUid         = headers.get("uid").get(0);
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public String getClient() {
        return mClient;
    }

    public String getExpiry() {
        return mExpiry;
    }

    public String getTokenType() {
        return mTokenType;
    }

    public String getUid() {
        return mUid;
    }

    @Override
    public User get() {
        return mUserResponse.get();
    }
}
