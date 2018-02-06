package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

/**
 * Created by Alex on 21/01/2018.
 */

public class UserResponse extends BaseResponse<User> {

    @SerializedName("email")
    @Expose
    private String mEmail;

    @SerializedName("first_name")
    @Expose
    private String mFirstName;

    @SerializedName("last_name")
    @Expose
    private String mLastName;

    @Override
    public User get() {
        return new User(mEmail, mFirstName, mLastName);
    }
}
