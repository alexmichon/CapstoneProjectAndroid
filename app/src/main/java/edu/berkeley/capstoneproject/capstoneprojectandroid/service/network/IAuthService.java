package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import io.reactivex.Single;

/**
 * Created by Alex on 20/11/2017.
 */

public interface IAuthService {

    Single<User> doLogin(String email, String password);
    Single<User> doRegister(String email, String password, String passwordConfirmation, String firstName, String lastName);

    Single<User> doRestoreAuthentication(Authentication authentication);
}
