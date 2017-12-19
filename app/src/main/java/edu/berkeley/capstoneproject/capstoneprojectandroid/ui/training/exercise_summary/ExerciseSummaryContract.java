package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.Optional;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;

/**
 * Created by Alex on 18/12/2017.
 */

public interface ExerciseSummaryContract {

    interface View extends IBaseView {

        void onStartExercise();

        void moveBack();

        void showExerciseGoalEditDialog();

        void setExerciseGoalType(ExerciseGoal.Type type);

        void dismissExerciseGoalEditDialog();
    }

    interface Interactor extends IBaseInteractor {

        Single<ExerciseGoal> doGetCurrentExerciseGoal();

        Completable doSetExerciseGoal(ExerciseGoal exerciseGoal);
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {

        void onStartClick();

        void onBackClick();

        void loadExerciseSummary();

        void onExerciseGoalEdit();
        void onExerciseGoalDone(ExerciseGoal exerciseGoal);
    }
}
