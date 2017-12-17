package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_result;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Single;

/**
 * Created by Alex on 17/12/2017.
 */

public interface ExerciseResultContract {

    interface View extends IBaseView {

        void onExerciseResultLoaded(ExerciseResult exerciseResult);
    }

    interface Interactor extends IBaseInteractor {

        Single<ExerciseResult> doGetExerciseResult(Exercise exercise);
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onOkClick(ExerciseResult exerciseResult);
    }
}
