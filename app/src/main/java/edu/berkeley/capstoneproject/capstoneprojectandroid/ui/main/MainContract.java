package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.support.design.widget.NavigationView;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuItem;
import io.reactivex.Completable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Alex on 08/11/2017.
 */

public interface MainContract {

    interface View extends IBaseView {
        void showHomeFragment();

        void moveToAuthenticationActivity();
    }

    interface Interactor extends IBaseInteractor {

        Completable doLogout();
    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onMainMenuItemClick(MainMenuItem item);
    }
}
