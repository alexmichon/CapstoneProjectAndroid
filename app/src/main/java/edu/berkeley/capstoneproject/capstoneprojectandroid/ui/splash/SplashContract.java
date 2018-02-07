package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.net.NetworkInfo;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Single;

/**
 * Created by Alex on 07/11/2017.
 */

public interface SplashContract {

    interface View extends IBaseView {
        void updateMessage(String msg);
        void promptEnableNetwork();
        void stop();
        void done();

        void moveToAuthenticationActivity(Authentication authentication);

        void checkPermissions();

        void moveToMainActivity();
    }

    interface Interactor extends IBaseInteractor {

        Single<NetworkInfo> doCheckNetworkState();
        Single<User> doRestoreAuthentication();
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onStart();

        void onPermissionResult(String permission, int result);

        void onPermissionCheckDone();
    }
}
