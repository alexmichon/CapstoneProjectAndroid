package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.Optional;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

/**
 * Created by Alex on 18/12/2017.
 */

public interface ExerciseSummaryContract {

    interface View extends IBaseView {

        void onStartExercise();

        void moveBack();

        void setExerciseGoalType(String type);
    }

    interface Interactor extends IBaseInteractor {

        Single<Optional<ExerciseGoal>> doGetCurrentExerciseGoal();
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        void onStartClick();

        void onBackClick();

        void loadExerciseSummary();
    }
}
