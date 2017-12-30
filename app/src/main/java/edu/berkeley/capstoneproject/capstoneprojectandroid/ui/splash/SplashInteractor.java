package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Alex on 10/11/2017.
 */

public class SplashInteractor extends BaseInteractor implements SplashContract.Interactor {

    private final ConnectivityManager mConnectivityManager;
    private final IAuthManager mAuthManager;

    @Inject
    public SplashInteractor(IDataManager dataManager, ConnectivityManager connectivityManager, IAuthManager authManager) {
        super(dataManager);
        mConnectivityManager = connectivityManager;
        mAuthManager = authManager;
    }

    @Override
    public Single<NetworkInfo> doCheckNetworkState() {
        return Single.just(mConnectivityManager.getActiveNetworkInfo());
    }

    @Override
    public Single<User> restore() {
        return mAuthManager.restore();
    }
}
