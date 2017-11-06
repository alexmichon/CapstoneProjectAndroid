package edu.berkeley.capstoneproject.capstoneprojectandroid.models.users;

/**
 * Created by Alex on 05/11/2017.
 */

public class User {

    private static final String TAG = User.class.getSimpleName();


    public static final int HANDLER_MESSAGE_LOGIN = 0;
    public static final int HANDLER_LOGIN_SUCCESS = 0;
    public static final int HANDLER_LOGIN_FAILURE = 1;


    private String mEmail;
    private String mPassword;
    private String mAuthenticationToken;
    private boolean mAuthenticated = false;

    public User(String email, String password) {
        mEmail = email;
        mPassword = password;
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

    public boolean isAuthenticated() {
        return mAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        mAuthenticated = authenticated;
    }

}
