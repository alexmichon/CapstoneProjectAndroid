package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;

/**
 * Created by Alex on 13/01/2018.
 */

public interface ExerciseBuilderContract {

    interface View extends IBaseView {
        void onExerciseBuilt();
    }

    interface Interactor extends IBaseInteractor {

    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void selectExerciseType(ExerciseType exerciseType);
    }
}
