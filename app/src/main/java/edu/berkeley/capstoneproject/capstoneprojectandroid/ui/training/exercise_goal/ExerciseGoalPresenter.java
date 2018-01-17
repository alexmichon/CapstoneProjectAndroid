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

    @Inject
    public ExerciseGoalPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadExerciseGoal() {
        if (isViewAttached()) {
            getView().onExerciseGoalLoading();
        }

        getCompositeDisposable().add(getInteractor().doGetExerciseGoal()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExerciseGoal>() {
                    @Override
                    public void accept(ExerciseGoal exerciseGoal) throws Exception {
                        if (isViewAttached()) {
                            getView().onExerciseGoalLoaded(exerciseGoal);
                        }
                    }
                })
        );
    }

}
