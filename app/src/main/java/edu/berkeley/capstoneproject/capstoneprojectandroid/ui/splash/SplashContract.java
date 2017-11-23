package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 07/11/2017.
 */

public interface SplashContract {

    interface View extends IBaseView {
        void updateMessage(String msg);
        void promptEnableNetwork();
        void stop();
        void done();
    }

    interface Interactor extends IBaseInteractor {

    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onStart();
    }
}
