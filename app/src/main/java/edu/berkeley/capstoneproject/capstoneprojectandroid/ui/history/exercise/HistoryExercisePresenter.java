package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercise;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 16/12/2017.
 */

public class HistoryExercisePresenter<V extends HistoryExerciseContract.View, I extends HistoryExerciseContract.Interactor> extends BasePresenter<V, I> implements HistoryExerciseContract.Presenter<V, I> {

    private Exercise mExercise;

    @Inject
    public HistoryExercisePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadExerciseResult() {
        if (isViewAttached()) {
            getView().onExerciseResultLoading();
        }

        getCompositeDisposable().add(getInteractor().doGetExerciseResult(mExercise)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<ExerciseResult>() {
                    @Override
                    public void accept(ExerciseResult exerciseResult) throws Exception {
                        if (isViewAttached()) {
                            getView().onExerciseResultLoaded(exerciseResult);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable);
                        if (isViewAttached()) {
                            getView().onExerciseResultError(throwable);
                        }
                    }
                })
        );
    }

    @Override
    public void setExercise(Exercise exercise) {
        mExercise = exercise;
    }
}
