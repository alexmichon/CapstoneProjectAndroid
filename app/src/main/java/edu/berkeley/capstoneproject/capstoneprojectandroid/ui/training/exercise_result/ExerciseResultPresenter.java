package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_result;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultPresenter<V extends ExerciseResultContract.View, I extends ExerciseResultContract.Interactor> extends BasePresenter<V, I> implements ExerciseResultContract.Presenter<V, I> {

    @Inject
    public ExerciseResultPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onMenuClick() {
        if (isViewAttached()) {
            getView().menu();
        }
    }

    @Override
    public void onRetryClick() {
        if (isViewAttached()) {
            getView().retry();
        }
    }

    @Override
    public void loadExerciseResult() {
        if (isViewAttached()) {
            getView().onExerciseResultLoading();
        }

        getCompositeDisposable().add(getInteractor().doGetExerciseResult()
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
}
