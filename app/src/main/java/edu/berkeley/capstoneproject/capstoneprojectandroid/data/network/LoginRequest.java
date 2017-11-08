package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;

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
}
