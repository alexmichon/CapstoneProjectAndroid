package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_goal;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Completable;

/**
 * Created by Alex on 17/12/2017.
 */

public interface ExerciseGoalContract {

    interface View extends IBaseView {
        void onDone(ExerciseGoal exerciseGoal);
    }

    interface Interactor extends IBaseInteractor {
        Completable doSaveExerciseGoal(ExerciseGoal exerciseGoal);
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onOkClick(ExerciseGoal exerciseGoal);
    }
}
