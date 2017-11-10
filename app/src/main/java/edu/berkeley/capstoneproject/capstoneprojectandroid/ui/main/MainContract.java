package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scopes.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 08/11/2017.
 */

public interface MainContract {

    interface View extends IBaseView {
        void showError(String message);
        void startBluetoothListActivity();
    }

    interface Interactor extends IBaseInteractor {

    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onStartExerciseClick();
        void onViewResultsClick();
    }
}
