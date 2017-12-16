package edu.berkeley.capstoneproject.capstoneprojectandroid.data.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

/**
 * Created by Alex on 15/12/2017.
 */

@Singleton
public class SessionHelper implements ISessionHelper {

    private User mCurrentUser;

    @Inject
    public SessionHelper() {

    }

    @Override
    public User getCurrentUser() {
        return mCurrentUser;
    }

    @Override
    public void setCurrentUser(User user) {
        mCurrentUser = user;
    }
}
