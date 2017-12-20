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
    public void loadCurrentExerciseGoal() {
        if (isViewAttached()) {
            getView().showLoading();
        }

        getCompositeDisposable().add(getInteractor().doLoadCurrentExerciseGoal()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExerciseGoalCreator>() {
                    @Override
                    public void accept(ExerciseGoalCreator exerciseGoalCreator) throws Exception {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().setExerciseGoalType(exerciseGoalCreator.getType());
                            getView().setMetricGoals(exerciseGoalCreator.getMetricGoals());
                        }
                    }
                })
        );
    }

    @Override
    public void loadDefaultExerciseGoal() {
        if (isViewAttached()) {
            getView().showLoading();
        }

        getCompositeDisposable().add(getInteractor().doLoadDefaultExerciseGoal()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExerciseGoalCreator>() {
                    @Override
                    public void accept(ExerciseGoalCreator exerciseGoalCreator) throws Exception {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            if (getView().getExerciseGoalType() == ExerciseGoal.Type.DEFAULT) {
                                getView().setMetricGoals(exerciseGoalCreator.getMetricGoals());
                            }
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

        ExerciseGoal.Type type = getView().getExerciseGoalType();
        List<MetricGoal> metricGoals = getView().getMetricGoals();

        getCompositeDisposable().add(getInteractor().doUpdateExerciseGoal(type, metricGoals)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().onExerciseGoalEditDone();
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
