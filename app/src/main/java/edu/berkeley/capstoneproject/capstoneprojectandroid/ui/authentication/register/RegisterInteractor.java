package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationFragmentInteractor;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 10/11/2017.
 */

public class RegisterInteractor extends AuthenticationFragmentInteractor implements RegisterContract.Interactor {

    @Inject
    public RegisterInteractor(IDataManager dataManager, IAuthManager authManager) {
        super(dataManager, authManager);
    }

    @Override
    public Single<User> doRegisterApiCall(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        return getAuthManager().register(email, password, passwordConfirmation, firstName, lastName);
    }
}
