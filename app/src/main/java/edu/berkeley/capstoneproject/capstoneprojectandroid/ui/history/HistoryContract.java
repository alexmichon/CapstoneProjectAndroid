package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 06/12/2017.
 */

public interface HistoryContract {

    interface View extends IBaseView {

        void showHistoryExercisesFragment();

        void showHistoryExerciseFragment(Exercise exercise);
    }

    interface Interactor extends IBaseInteractor {

    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        void onHistoryExerciseSelect(Exercise exercise);
    }
}
