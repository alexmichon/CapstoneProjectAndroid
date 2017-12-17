package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.support.design.widget.NavigationView;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuItem;

/**
 * Created by Alex on 08/11/2017.
 */

public interface MainContract {

    interface View extends IBaseView {
        void showHomeFragment();
    }

    interface Interactor extends IBaseInteractor {

    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onMainMenuItemClick(MainMenuItem item);
    }
}
