package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.AuthenticationFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.UserFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiError;
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
        if (email.equals("admin") && password.equals("admin")) {
            return Single.just(UserFactory.admin());
        }
        return Single.error(new ApiError(ApiError.ERROR_UNAUTHORIZED, "Login failed"));
    }

    @Override
    public Single<User> doRegister(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        User user = new User(email, firstName, lastName);
        user.setAuthentication(
                AuthenticationFactory.defaultBuilder()
                    .build()
        );
        return Single.just(user);
    }

    @Override
    public Single<User> doRestoreAuthentication(Authentication authentication) {
        return Single.just(UserFactory.builder()
                .withAuthentication(authentication)
                .withEmail(authentication.getUid())
                .build()
        );
    }
}
