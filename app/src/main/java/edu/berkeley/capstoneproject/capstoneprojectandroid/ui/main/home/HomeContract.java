package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.home;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 10/11/2017.
 */

public interface HomeContract {

    interface View extends IBaseView {

        void startTrainingActivity();

        void startResultsActivity();
    }

    interface Interactor extends IBaseInteractor {

    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onStartTrainingClick();

        void onViewResultsClick();
    }
}
