package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryExercisesPresenter<V extends HistoryExercisesContract.View, I extends HistoryExercisesContract.Interactor> extends BasePresenter<V, I> implements HistoryExercisesContract.Presenter<V, I> {

    @Inject
    public HistoryExercisesPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void loadExercises() {
        if (isViewAttached()) {
            getView().onLoadingExercises(new IBaseView.OnCancelListener() {
                @Override
                public void onCancel() {
                    cancelLoadExercises();
                }
            });
        }

        getCompositeDisposable().add(getInteractor().doGetExercises()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableObserver<Exercise>() {
                    @Override
                    public void onNext(@NonNull Exercise exercise) {
                        if (isViewAttached()) {
                            getView().addExercise(exercise);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (isViewAttached()) {
                            getView().showError(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached()) {
                            getView().onExercisesLoaded();
                        }
                    }
                })
        );
    }

    @Override
    public void onHistoryExerciseSelect(Exercise exercise) {
        if (isViewAttached()) {
            getView().selectHistoryExercise(exercise);
        }
    }

    protected void cancelLoadExercises() {
        getCompositeDisposable().dispose();
    }
}
