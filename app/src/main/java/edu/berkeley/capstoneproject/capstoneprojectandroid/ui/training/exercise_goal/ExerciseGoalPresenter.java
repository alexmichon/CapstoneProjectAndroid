package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal;

import java.util.List;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalPresenter<V extends ExerciseGoalContract.View, I extends ExerciseGoalContract.Interactor> extends BasePresenter<V, I> implements ExerciseGoalContract.Presenter<V, I> {

    ExerciseGoal mExerciseGoal;

    @Inject
    public ExerciseGoalPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadExerciseGoalInfo() {
        if (isViewAttached()) {
            getView().setMetricGoals(mExerciseGoal.getMetricGoals());
        }
    }

    @Override
    public void setExerciseGoal(ExerciseGoal exerciseGoal) {
        mExerciseGoal = exerciseGoal;
    }

}
