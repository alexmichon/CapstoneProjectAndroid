package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;

/**
 * Created by Alex on 17/12/2017.
 */

public interface IUserService {

    User getCurrentUser();
    void setCurrentUser(User user);
}
