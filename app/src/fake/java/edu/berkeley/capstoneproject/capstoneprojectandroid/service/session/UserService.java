package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

/**
 * Created by Alex on 17/12/2017.
 */

@Singleton
public class UserService implements IUserService {

    private User mCurrentUser;

    @Inject
    public UserService() {

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
