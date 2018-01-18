package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

/**
 * Created by Alex on 20/11/2017.
 */

public class ApiHeader {

    private Authentication mAuthentication;

    @Inject
    public ApiHeader() {

    }

    public Authentication getAuthentication() {
        return mAuthentication;
    }

    public void setAuthentication(Authentication authentication) {
        mAuthentication = authentication;
    }
}
