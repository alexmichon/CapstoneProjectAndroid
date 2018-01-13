package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationFragmentInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by Alex on 10/11/2017.
 */

public class LoginInteractor extends AuthenticationFragmentInteractor implements LoginContract.Interactor {

    @Inject
    public LoginInteractor(IDataManager dataManager, IAuthManager authManager) {
        super(dataManager, authManager);
    }
    
    @Override
    public Single<User> doLoginCall(String email, String password) {
        return getAuthManager().login(email, password);
    }
}
