package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user;

/**
 * Created by Alex on 24/12/2017.
 */

public class GuestUser extends User {

    private static final String EMAIL = "";
    private static final String FIRST_NAME = "Guest";
    private static final String LAST_NAME = "";

    public GuestUser() {
        super(EMAIL, FIRST_NAME, LAST_NAME);
    }
}
