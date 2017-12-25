package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Timed;
import timber.log.Timber;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExercisePresenter<V extends ExerciseContract.View, I extends ExerciseContract.Interactor>
    extends BasePresenter<V, I> implements ExerciseContract.Presenter<V, I> {

    private static final int COUNTDOWN = 3;

    private boolean mRecording = false;

    private Exercise mExercise;
    private ExerciseType mExerciseType;

    @Inject
    public ExercisePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void attachView(@NonNull V view) {
        super.attachView(view);
        mExerciseType = view.getExerciseType();
    }

    @Override
    public boolean isRecording() {
        return mRecording;
    }


    @Override
    public void onCreate() {
        if (isViewAttached()) {
            getView().onCreatingExercise();
        }

        getCompositeDisposable().add(getInteractor().doCreateExercise(mExerciseType)
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

                        if (isViewAttached()) {
                            getView().onExerciseReady(exercise);
                        }

                        startListening(exercise);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable, "Error while starting exercise");
                        mRecording = false;

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

    protected void startCountdown() {
        if (isViewAttached()) {
            getView().onCountdownStart();
        }

        getCompositeDisposable().add(Observable.interval(1, TimeUnit.SECONDS)
                .take(COUNTDOWN + 1, TimeUnit.SECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()) {
                            getView().onCountdownFinished();
                        }
                    }
                })
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (isViewAttached()) {
                            getView().onCountdownUpdate(COUNTDOWN - aLong.intValue());
                        }
                    }
                })
        );
    }

    @Override
    public void onStopClick() {
        mRecording = false;
        getInteractor().doStopExercise();
        getCompositeDisposable().clear();

        if (isViewAttached()) {
            getView().onExerciseStopped(mExercise);
        }
    }

    @Override
    public void onStartRecording() {
        mRecording = true;
        if (isViewAttached()) {
            getView().onStartRecording();
        }

        getCompositeDisposable().add(Observable.interval(10, TimeUnit.MILLISECONDS)
                .timeInterval(TimeUnit.MILLISECONDS)
                .take(mExercise.getDuration(), TimeUnit.SECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mRecording = false;
                        if (isViewAttached()) {
                            getView().onExerciseFinished();
                        }
                    }
                })
                .subscribe(new Consumer<Timed<Long>>() {
                    @Override
                    public void accept(Timed<Long> aLong) throws Exception {
                        if (isViewAttached()) {
                            getView().onTimerUpdate((float)aLong.value() / 100);
                        }
                    }
                })
        );
    }

    @Override
    public void onStopRecording() {
        mRecording = false;
    }

    @Override
    public void onPause() {
        mRecording = false;
        getInteractor().doStopExercise();

        if (isViewAttached()) {
            getView().onExerciseStopped(mExercise);
        }
    }

    @Override
    public void onStartClick() {
        startCountdown();
    }

    protected void onReceiveMeasurement(Exercise exercise, Measurement measurement) {
        if (mRecording) {
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
                            handleApiError(throwable);
                        }
                    })
            );
        }
    }
}
