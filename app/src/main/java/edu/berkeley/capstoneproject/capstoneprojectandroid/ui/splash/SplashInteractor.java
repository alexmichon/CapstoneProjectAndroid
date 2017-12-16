package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;

/**
 * Created by Alex on 10/11/2017.
 */

public class SplashInteractor extends BaseInteractor implements SplashContract.Interactor {

    @Inject
    public SplashInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<Authentication> doGetStoredAuthentication() {
        return Single.just(getDataManager().getPreferencesHelper().getAuthentication());
    }

    @Override
    public Single<User> doRestoreAuthentication(Authentication authentication) {
        return getDataManager().getApiHelper().getAuthService().doRestoreAuthentication(authentication);
    }
}
