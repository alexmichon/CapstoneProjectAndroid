package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 07/11/2017.
 */

public class LoginRequest {

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("password")
    @Expose
    private String mPassword;

    public LoginRequest(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        LoginRequest that = (LoginRequest) obj;

        if (mEmail != null ? !mEmail.equals(that.mEmail) : that.mEmail != null) return false;
        return mPassword != null ? mPassword.equals(that.mPassword) : that.mPassword == null;
    }
}
