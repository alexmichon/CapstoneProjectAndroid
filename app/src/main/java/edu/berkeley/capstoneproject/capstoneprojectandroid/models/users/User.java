package edu.berkeley.capstoneproject.capstoneprojectandroid.models.users;

/**
 * Created by Alex on 05/11/2017.
 */

public class User {

    private static final String TAG = User.class.getSimpleName();

    private String mEmail;
    private String mPassword;
    private String mPasswordConfirmation;
    private String mFirstName;
    private String mLastName;

    private String mAuthenticationToken;
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

    public String getFirstName() {
        return mFirstName;
    }

    public String getPasswordConfirmation() {
        return mPasswordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        mPasswordConfirmation = passwordConfirmation;
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

}
