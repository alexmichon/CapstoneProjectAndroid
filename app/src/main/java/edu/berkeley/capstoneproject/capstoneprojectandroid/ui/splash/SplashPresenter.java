package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;

/**
 * Created by Alex on 07/11/2017.
 */

public class SplashPresenter extends BasePresenter<SplashContract.View> implements SplashContract.Presenter {

    private static final String TAG = SplashPresenter.class.getSimpleName();

    private ConnectivityManager mConnectivityManager;

    public SplashPresenter(SplashContract.View view, ConnectivityManager connectivityManager) {
        super(view);
        mConnectivityManager = connectivityManager;
    }

    @Override
    public void start() {
        checkNetworkState();
        mView.done();
    }

    public void checkNetworkState() {
        Log.d(TAG, "Checking network state");
        mView.updateMessage("Checking network state...");

        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info == null || info.getState() != NetworkInfo.State.CONNECTED) {
            mView.stop();
        }
    }

}
