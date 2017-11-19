package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import android.support.design.widget.NavigationView;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.scope.PerActivity;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 17/11/2017.
 */

public interface TrainingContract {

    interface View extends IBaseView {
        void showBluetoothListFragment();

        void showExerciseTypesFragment();

        void showExerciseFragment(ExerciseType exerciseType);
    }

    interface Interactor extends IBaseInteractor {

    }

    @PerActivity
    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V,I> {
        NavigationView.OnNavigationItemSelectedListener getNavigationListener();
    }
}
