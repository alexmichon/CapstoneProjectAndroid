package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Alex on 30/12/2017.
 */

public interface IAuthManager {

    User getCurrentUser();
    void setCurrentUser(User user);

    Single<User> doLogin(String email, String password);
    Single<User> doRegister(String email, String password, String passwordConfirmation, String firstName, String lastName);
    Completable doLogout();
    Single<User> restore();

    void remember(boolean enabled);
}
