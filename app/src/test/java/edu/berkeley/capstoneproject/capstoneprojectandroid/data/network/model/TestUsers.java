package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

/**
 * Created by Alex on 25/11/2017.
 */

public final class TestUsers {

    public static User.UserBuilder defaultUser() {
        return new User.UserBuilder()
                .email("toto@toto.fr")
                .firstName("toto")
                .lastName("lovespizza");
    }
}
