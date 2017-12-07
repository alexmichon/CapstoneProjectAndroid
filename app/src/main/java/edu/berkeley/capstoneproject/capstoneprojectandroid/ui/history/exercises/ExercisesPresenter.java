package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 06/12/2017.
 */

public class ExercisesPresenter<V extends ExercisesContract.View, I extends ExercisesContract.Interactor> extends BasePresenter<V, I> implements ExercisesContract.Presenter<V, I> {

    @Inject
    public ExercisesPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
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
                .subscribe(new Consumer<Exercise>() {
                    @Override
                    public void accept(Exercise exercise) throws Exception {
                        if (isViewAttached()) {
                            getView().addExercise(exercise);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (isViewAttached()) {
                            getView().showError(throwable);
                        }
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()) {
                            getView().onExercisesLoaded();
                        }
                    }
                })
        );
    }

    protected void cancelLoadExercises() {
        getCompositeDisposable().dispose();
    }
}
