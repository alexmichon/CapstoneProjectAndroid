package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model;

import org.junit.Before;
import org.junit.Test;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Alex on 25/11/2017.
 */

public class UserTest {

    private static final String TEST_EMAIL = "toto@toto.fr";
    private static final String TEST_FIRST_NAME = "toto";
    private static final String TEST_LAST_NAME = "lovespizza";


    private User mUser;

    @Before
    public void setup() {
        mUser = new User(TEST_EMAIL, TEST_FIRST_NAME, TEST_LAST_NAME);
    }

    @Test
    public void userGetEmail() {
        assertEquals(TEST_EMAIL, mUser.getEmail());
    }

    @Test
    public void userGetFirstName() {
        assertEquals(TEST_FIRST_NAME, mUser.getFirstName());
    }

    @Test
    public void userGetLastName() {
        assertEquals(TEST_LAST_NAME, mUser.getLastName());
    }

    @Test
    public void userSetEmail() {
        String newEmail = "foo@foo.com";
        mUser.setEmail(newEmail);
        assertEquals(newEmail, mUser.getEmail());
    }

    @Test
    public void userSetFirstName() {
        String newFirstName = "foo";
        mUser.setFirstName(newFirstName);
        assertEquals(newFirstName, mUser.getFirstName());
    }

    @Test
    public void userSetLastName() {
        String newLastName = "foo";
        mUser.setLastName(newLastName);
        assertEquals(newLastName, mUser.getLastName());
    }
}
