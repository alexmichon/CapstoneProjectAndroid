package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;


import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuItem;
import io.reactivex.Completable;

/**
 * Created by Alex on 08/11/2017.
 */

public interface MainContract {

    interface View extends IBaseView {
        void startToAuthenticationActivity();

        void showHomeFragment();
    }

    interface Interactor extends IBaseInteractor {

        Completable doLogout();
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onMainMenuItemClick(MainMenuItem item);
    }
}
