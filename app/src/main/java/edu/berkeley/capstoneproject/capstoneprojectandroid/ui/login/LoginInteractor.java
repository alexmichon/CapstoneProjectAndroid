package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 10/11/2017.
 */

public class LoginInteractor extends BaseInteractor implements LoginContract.Interactor {

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
                        updateApiHeader(user);
                    }
                });
    }

    @Override
    public void doRemember(User user) {
        getDataManager().getPreferencesHelper().setAuthentication(user.getAuthentication());
    }


    private void updateApiHeader(User user) {
        Timber.d("Updating api header");
        getDataManager().getApiHelper().getApiHeader().setAuthentication(user.getAuthentication());
    }
}
