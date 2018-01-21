package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Single;

/**
 * Created by Alex on 08/12/2017.
 */

public interface HistoryExerciseContract {

    interface View extends IBaseView {

        void onExerciseResultError(Throwable throwable);

        void onExerciseResultLoading();

        void onExerciseLoaded(Exercise exercise);

        void onExerciseResultLoaded(ExerciseResult exerciseResult);
    }

    interface Interactor extends IBaseInteractor {
        Single<ExerciseResult> doGetExerciseResult(Exercise exercise);
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void setExercise(Exercise exercise);

        void loadExerciseInfo();
        void loadExerciseResult();
    }
}
