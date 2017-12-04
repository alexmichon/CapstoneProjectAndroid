package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import io.reactivex.Single;

/**
 * Created by Alex on 28/11/2017.
 */

public class AuthService implements IAuthService {

    @Inject
    public AuthService() {

    }

    @Override
    public Single<User> doLogin(String email, String password) {
        return Single.never();
    }

    @Override
    public Single<User> doRegister(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        return Single.never();
    }
}
