package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 07/11/2017.
 */

public class SplashPresenter<V extends SplashContract.View, I extends SplashContract.Interactor>
        extends BasePresenter<V, I> implements SplashContract.Presenter<V, I> {

    private ConnectivityManager mConnectivityManager;

    private Authentication mAuthentication;
    private boolean mIsLoggedIn = false;

    @Inject
    public SplashPresenter(I interactor,
                           ISchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable,
                           ConnectivityManager connectivityManager) {
        super(interactor, schedulerProvider, compositeDisposable);
        mConnectivityManager = connectivityManager;
    }

    @Override
    public void onStart() {
        checkNetworkState();
        getStoredAuthentication();
    }

    public void checkNetworkState() {
        Timber.d("Checking network state");

        if (isViewAttached()) {
            getView().updateMessage("Checking network state...");
        }

        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info == null || info.getState() != NetworkInfo.State.CONNECTED) {
            getView().stop();
        }
    }

    protected void getStoredAuthentication() {
        if (isViewAttached()) {
            getView().updateMessage("Loading authentication credentials");
        }

        getCompositeDisposable().add(getInteractor().doGetStoredAuthentication()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Authentication>() {
                    @Override
                    public void accept(Authentication authentication) throws Exception {
                        mAuthentication = authentication;
                        restoreAuthentication(authentication);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mIsLoggedIn = false;
                    }
                })
        );
    }

    protected void restoreAuthentication(Authentication authentication) {
        if (isViewAttached()) {
            getView().updateMessage("Restoring credentials");
        }

        getCompositeDisposable().add(getInteractor().doRestoreAuthentication(authentication)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        mIsLoggedIn = true;
                        onStartDone();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mIsLoggedIn = false;
                        onStartDone();
                    }
                })
        );
    }

    protected void onStartDone() {
        if (isViewAttached()) {
            getView().done();

            if (!mIsLoggedIn) {
                getView().moveToAuthenticationActivity(mAuthentication);
            }
            else {
                getView().moveToMainActivity();
            }
        }
    }

}
