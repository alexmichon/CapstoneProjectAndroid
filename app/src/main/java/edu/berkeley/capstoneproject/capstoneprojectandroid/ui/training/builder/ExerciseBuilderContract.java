package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Single;

/**
 * Created by Alex on 13/01/2018.
 */

public interface ExerciseBuilderContract {

    interface View extends IBaseView {
        void onExerciseBuilt();
        void onExerciseError(Throwable throwable);
    }

    interface Interactor extends IBaseInteractor {
        Single<Exercise> doCreateExercise(ExerciseType exerciseType);
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onExerciseTypeSelect(ExerciseType exerciseType);
    }
}
