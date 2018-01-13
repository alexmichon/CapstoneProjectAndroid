package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Alex on 17/12/2017.
 */

public interface ExerciseGoalContract {

    interface View extends IBaseView {
        void onCurrentExerciseGoalLoaded(ExerciseGoal exerciseGoal);

        void onDefaultExerciseGoalLoaded(ExerciseGoal exerciseGoal);

        void setMetricGoals(List<MetricGoal> metricGoals);

        void onExerciseGoalEditDone();

        void setExerciseGoalType(ExerciseGoal.Type t);
        ExerciseGoal.Type getExerciseGoalType();

        List<MetricGoal> getMetricGoals();
    }

    interface Interactor extends IBaseInteractor {
        //Single<ExerciseGoalCreator> doLoadDefaultExerciseGoal();
        Single<ExerciseGoalCreator> doLoadCurrentExerciseGoal();

        Completable doUpdateExerciseGoal(ExerciseGoal.Type type, List<MetricGoal> metricGoals);
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void loadCurrentExerciseGoal();
        void loadDefaultExerciseGoal();

        void onSaveExerciseGoal();
    }
}
