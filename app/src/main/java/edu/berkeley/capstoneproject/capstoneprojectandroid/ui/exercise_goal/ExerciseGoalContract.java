package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_goal;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

/**
 * Created by Alex on 17/12/2017.
 */

public interface ExerciseGoalContract {

    interface View extends IBaseView {
        void onExerciseGoalLoaded(ExerciseGoal exerciseGoal);

        void onDone(ExerciseGoal exerciseGoal);
    }

    interface Interactor extends IBaseInteractor {
        Completable doSaveExerciseGoal(ExerciseGoal exerciseGoal);

        Single<ExerciseGoal> doGetExerciseGoal(ExerciseType exerciseType);
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void loadExerciseGoal(ExerciseType exerciseType);

        void onOkClick();
    }
}
