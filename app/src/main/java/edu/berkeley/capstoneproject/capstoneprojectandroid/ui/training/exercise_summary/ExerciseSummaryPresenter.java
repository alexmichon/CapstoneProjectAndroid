package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_summary;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
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
            getView().onStartExercise();
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
        if (isViewAttached()) {
            getView().showLoading();
        }

        getCompositeDisposable().add(getInteractor().doGetCurrentExerciseGoal()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExerciseGoalCreator>() {
                    @Override
                    public void accept(ExerciseGoalCreator exerciseGoalCreator) throws Exception {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().setExerciseGoalType(exerciseGoalCreator.getType());
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

    @Override
    public void onExerciseGoalEdit() {
        if (isViewAttached()) {
            getView().showExerciseGoalEditDialog();
        }
    }

    @Override
    public void onExerciseGoalDone() {
        if (isViewAttached()) {
            getView().dismissExerciseGoalEditDialog();
        }
    }
}
