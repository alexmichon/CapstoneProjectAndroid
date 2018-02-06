package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Alex on 07/11/2017.
 */

public class RegisterRequest extends BaseRequest {

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

    public RegisterRequest(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        mEmail = email;
        mPassword = password;
        mPasswordConfirmation = passwordConfirmation;
        mFirstName = firstName;
        mLastName = lastName;
    }
}
