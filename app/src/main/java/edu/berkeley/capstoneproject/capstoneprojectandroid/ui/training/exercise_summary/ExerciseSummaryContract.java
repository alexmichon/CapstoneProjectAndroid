package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 18/12/2017.
 */

public interface ExerciseSummaryContract {

    interface View extends IBaseView {

        void onStartExercise();
    }

    interface Interactor extends IBaseInteractor {

    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        void onStartClick();
    }
}
