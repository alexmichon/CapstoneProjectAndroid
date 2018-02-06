package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationFragmentInteractor;
import io.reactivex.Single;

/**
 * Created by Alex on 10/11/2017.
 */

public class LoginInteractor extends AuthenticationFragmentInteractor implements LoginContract.Interactor {

    @Inject
    public LoginInteractor(IAuthManager authManager) {
        super(authManager);
    }
    
    @Override
    public Single<User> doLogin(String email, String password) {
        return getAuthManager().doLogin(email, password);
    }
}
