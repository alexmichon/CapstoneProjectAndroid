package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

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
        void moveToMainActivity();
    }

    interface Interactor extends IBaseInteractor {

        Single<Authentication> doGetStoredAuthentication();

        Single<User> doRestoreAuthentication(Authentication authentication);
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onStart();
    }
}
