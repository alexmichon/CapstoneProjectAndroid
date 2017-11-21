package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user;

/**
 * Created by Alex on 07/11/2017.
 */

public class User {

    private String mEmail;
    private String mFirstName;
    private String mLastName;

    public User(String email, String firstName, String lastName) {
        mEmail = email;
        mFirstName = firstName;
        mLastName = lastName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
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
}
