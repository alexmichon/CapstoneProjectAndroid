package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
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
    public void loadExerciseDefaultGoal() {
        if (isViewAttached()) {
            getView().showLoading();
        }

        getCompositeDisposable().add(getInteractor().doGetExerciseGoal()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExerciseGoal>() {
                    @Override
                    public void accept(ExerciseGoal exerciseGoal) throws Exception {
                        if (isViewAttached()) {
                            getView().onExerciseGoalLoaded(exerciseGoal);
                            getView().setType(ExerciseGoal.Type.DEFAULT);
                        }
                    }
                })
        );
    }

    @Override
    public void onSaveExerciseGoal() {
        if (!isViewAttached()) {
            return;
        }

        List<MetricGoal> metricGoals = getView().getMetricGoals();

        getCompositeDisposable().add(getInteractor().doUpdateExerciseGoal(metricGoals)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnSuccess(new Consumer<ExerciseGoal>() {
                    @Override
                    public void accept(ExerciseGoal exerciseGoal) throws Exception {
                        getInteractor().doSetExerciseGoal(exerciseGoal);
                    }
                })
                .subscribe(new Consumer<ExerciseGoal>() {
                    @Override
                    public void accept(ExerciseGoal exerciseGoal) throws Exception {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().onExerciseGoalEditDone(exerciseGoal);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().showError(throwable);
                        }
                    }
                })
        );
    }
}
