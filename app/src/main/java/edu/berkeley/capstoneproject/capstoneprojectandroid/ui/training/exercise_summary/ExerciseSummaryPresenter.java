package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 18/12/2017.
 */

public class ExerciseSummaryPresenter<V extends ExerciseSummaryContract.View, I extends ExerciseSummaryContract.Interactor> extends BasePresenter<V, I> implements ExerciseSummaryContract.Presenter<V, I> {

    @Inject
    public ExerciseSummaryPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onStartClick() {
        if (isViewAttached()) {
            getView().startExercise();
        }
    }

    @Override
    public void onBackClick() {
        if (isViewAttached()) {
            getView().moveBack();
        }
    }

    @Override
    public void loadExerciseSummary() {

    }
}
