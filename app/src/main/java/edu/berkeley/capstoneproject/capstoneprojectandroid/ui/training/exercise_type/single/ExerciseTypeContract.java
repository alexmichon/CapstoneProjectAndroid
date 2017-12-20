package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.single;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Completable;

/**
 * Created by Alex on 09/12/2017.
 */

public interface ExerciseTypeContract {

    interface View extends IBaseView {
        void onExerciseTypeSelect(ExerciseType exerciseType);
    }

    interface Interactor extends IBaseInteractor {
    }

    interface Presenter<V extends IBaseView, I extends IBaseInteractor> extends IBasePresenter<V, I> {
        ExerciseType getExerciseType();
        void setExerciseType(ExerciseType exerciseType);
        void onExerciseTypeSelect();
    }
}
