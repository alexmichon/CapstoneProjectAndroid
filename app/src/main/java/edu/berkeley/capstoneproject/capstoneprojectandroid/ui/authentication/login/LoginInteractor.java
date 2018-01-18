package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.AuthenticationFragmentInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 10/11/2017.
 */

public class LoginInteractor extends AuthenticationFragmentInteractor implements LoginContract.Interactor {

    @Inject
    public LoginInteractor(IDataManager dataManager) {
        super(dataManager);
    }
    
    @Override
    public Single<User> doLoginCall(String email, String password) {
        return getDataManager().getApiHelper().getAuthService()
                .doLogin(email, password).doOnSuccess(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        setCurrentUser(user);
                        updateApiHeader(user);
                    }
                });
    }
}
