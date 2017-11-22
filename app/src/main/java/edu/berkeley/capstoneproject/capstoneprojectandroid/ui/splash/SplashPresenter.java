package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by Alex on 07/11/2017.
 */

public class SplashPresenter<V extends SplashContract.View, I extends SplashContract.Interactor>
        extends BasePresenter<V, I> implements SplashContract.Presenter<V, I> {

    private static final String TAG = SplashPresenter.class.getSimpleName();

    private ConnectivityManager mConnectivityManager;

    @Inject
    public SplashPresenter(I interactor,
                           ISchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable,
                           ConnectivityManager connectivityManager) {
        super(interactor, schedulerProvider, compositeDisposable);
        mConnectivityManager = connectivityManager;
    }

    @Override
    public void start() {
        checkNetworkState();
        getView().done();
    }

    public void checkNetworkState() {
        Timber.d("Checking network state");
        getView().updateMessage("Checking network state...");

        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info == null || info.getState() != NetworkInfo.State.CONNECTED) {
            getView().stop();
        }
    }

}
