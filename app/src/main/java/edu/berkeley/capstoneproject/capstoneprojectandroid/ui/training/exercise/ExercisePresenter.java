package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
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


    @Inject
    public ExercisePresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public boolean isRecording() {
        return mRecording;
    }

    @Override
    public void onViewReady() {
        if (isViewAttached()) {
            getView().onExerciseCreated(getInteractor().getExercise());
        }

        prepareExercise();
    }

    protected void prepareExercise() {
        if (isViewAttached()) {
            getView().onPreparingExercise();
        }

        getCompositeDisposable().add(getInteractor().doPrepareExercise()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("Exercise started");
                        startStreaming();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable, "Error while starting exercise");
                        mRecording = false;

                        if (isViewAttached()) {
                            getView().onExerciseError(throwable);
                        }
                    }
                })
                .subscribe()
        );
    }

    protected void startStreaming() {
        getCompositeDisposable().add(getInteractor().doStartStreaming()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("Exercise started");

                        if (isViewAttached()) {
                            getView().onExerciseReady(getInteractor().getExercise());
                        }

                        startListening();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable, "Error while starting exercise");
                        mRecording = false;
                        if (isViewAttached()) {
                            getView().onExerciseError(throwable);
                        }
                    }
                })
                .subscribe()
        );
    }

    protected void startListening() {
        getCompositeDisposable().add(getInteractor().doListen()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnNext(new Consumer<Measurement>() {
                    @Override
                    public void accept(Measurement measurement) throws Exception {
                        onReceiveMeasurement(measurement);
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable, "Listening error");

                        if (isViewAttached()) {
                            getView().showError(throwable);
                        }
                    }
                })
                .subscribe()
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
                        mRecording = true;
                        if (isViewAttached()) {
                            getView().onCountdownFinished();
                        }
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable);
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
    public void onStartRecording() {
        Timber.d("Start recording");
        mRecording = true;
        if (isViewAttached()) {
            getView().onStartRecording();
        }

        int duration = getInteractor().getExercise().getDuration();

        getCompositeDisposable().add(Observable.interval(10, TimeUnit.MILLISECONDS)
                .timeInterval(TimeUnit.MILLISECONDS)
                .take(duration, TimeUnit.SECONDS)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()) {
                            getView().onExerciseFinished();
                        }
                        stopExercise();
                    }
                })
                .doOnNext(new Consumer<Timed<Long>>() {
                    @Override
                    public void accept(Timed<Long> aLong) throws Exception {
                        if (isViewAttached()) {
                            getView().onTimerUpdate((float)aLong.value() / 100);
                        }
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable);
                    }
                })
                .subscribe()
        );
    }

    @Override
    public void onStartClick() {
        startCountdown();
    }

    protected void onReceiveMeasurement(Measurement measurement) {
        if (mRecording) {
            if (isViewAttached()) {
                getView().addMeasurement(measurement);
            }

            getInteractor().doSaveMeasurement(measurement);
        }
    }

    @Override
    public void onStopClick() {
        stopExercise();
    }

    protected void stopExercise() {
        mRecording = false;

        if (isViewAttached()) {
            getView().showLoading();
        }

        getCompositeDisposable().add(getInteractor().doStopExercise()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()) {
                            getView().onExerciseStopped();
                        }
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable, "An error occured while stopping the exercise");
                        if (isViewAttached()) {
                            getView().showError(throwable);
                        }
                    }
                })
                .subscribe()
        );
    }

    @Override
    public void onStopRecording() {
        mRecording = false;
    }

    @Override
    public void onPause() {
        stopExercise();
    }
}
