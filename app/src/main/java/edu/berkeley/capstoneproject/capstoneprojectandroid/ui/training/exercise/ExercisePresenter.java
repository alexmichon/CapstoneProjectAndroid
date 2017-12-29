package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import android.support.annotation.NonNull;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExercisePresenter<V extends ExerciseContract.View, I extends ExerciseContract.Interactor>
    extends BasePresenter<V, I> implements ExerciseContract.Presenter<V, I> {

    private boolean mStarted = false;

    private Exercise mExercise;

    @Inject
    public ExercisePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public boolean isStarted() {
        return mStarted;
    }


    @Override
    public void onStartClick() {
        if (isViewAttached()) {
            getView().onCreatingExercise();
        }

        getCompositeDisposable().add(getInteractor().doCreateExercise()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Exercise>() {
                    @Override
                    public void accept(Exercise exercise) throws Exception {
                        Timber.d("Exercise created");
                        mExercise = exercise;

                        if (isViewAttached()) {
                            getView().onExerciseCreated(exercise);
                        }
                        startExercise(exercise);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable);
                        if (isViewAttached()) {
                            getView().onExerciseError(throwable);
                        }
                    }
                })
        );
    }

    protected void startExercise(final Exercise exercise) {
        if (isViewAttached()) {
            getView().onStartingExercise();
        }

        getCompositeDisposable().add(getInteractor().doStartExercise(exercise)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("Exercise started");

                        mStarted = true;

                        if (isViewAttached()) {
                            getView().onExerciseStarted(exercise);
                        }

                        startListening(exercise);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable, "Error while starting exercise");
                        mStarted = false;

                        if (isViewAttached()) {
                            getView().onExerciseError(throwable);
                        }
                    }
                })
        );
    }

    protected void startListening(final Exercise exercise) {
        getCompositeDisposable().add(getInteractor().doListenMeasurements()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Measurement>() {
                               @Override
                               public void accept(Measurement measurement) throws Exception {
                                   onReceiveMeasurement(exercise, measurement);
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Timber.e(throwable, "Listening error");

                                if (isViewAttached()) {
                                    getView().showError(throwable);
                                }
                            }
                        })
        );
    }

    @Override
    public void onStopClick() {
        mStarted = false;

        if (isViewAttached()) {
            getView().showLoading();
        }

        getCompositeDisposable().add(getInteractor().doStopExercise()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().onExerciseStopped(mExercise);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable);
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().showError(throwable);
                        }
                    }
                })
        );
    }

    @Override
    public void onPause() {
        mStarted = false;

        if (isViewAttached()) {
            getView().onExerciseStopped(mExercise);
        }
    }

    protected void onReceiveMeasurement(Exercise exercise, Measurement measurement) {
        measurement.setExercise(exercise);

        if (isViewAttached()) {
            getView().addMeasurement(measurement);
        }

        getCompositeDisposable().add(getInteractor().doSaveMeasurement(exercise, measurement)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable);
                        handleApiError(throwable);
                    }
                })
        );
    }
}
