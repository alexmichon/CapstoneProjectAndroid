package edu.berkeley.capstoneproject.capstoneprojectandroid.data.session;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

/**
 * Created by Alex on 15/12/2017.
 */

public interface ISessionHelper {

    User getCurrentUser();
    void setCurrentUser(User user);
}
