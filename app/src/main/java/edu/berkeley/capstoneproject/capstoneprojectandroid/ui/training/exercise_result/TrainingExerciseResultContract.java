package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Single;

/**
 * Created by Alex on 18/01/2018.
 */

public interface TrainingExerciseResultContract {

    interface View extends IBaseView {

        void onExerciseResultError(Throwable throwable);

        void onExerciseResultLoading();

        void onExerciseResultLoaded(ExerciseResult exerciseResult);

        void goToMenu();
    }

    interface Interactor extends IBaseInteractor {
        Single<ExerciseResult> doGetExerciseResult();
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void loadExerciseResult();

        void onMenuClick();
    }
}
