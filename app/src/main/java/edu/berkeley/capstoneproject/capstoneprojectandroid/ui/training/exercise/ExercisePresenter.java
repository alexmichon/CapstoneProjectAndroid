package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
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
        startExercise();
    }

    protected void startExercise() {
        if (isViewAttached()) {
            getView().onStartingExercise();
        }

        getCompositeDisposable().add(getInteractor().doStartExercise()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        mStarted = true;
                        startStreaming();
                    }
                })
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        Timber.d("Exercise started");
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

    protected void startStreaming() {
        getCompositeDisposable().add(getInteractor().doStartStreaming()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()) {
                            getView().onExerciseStarted(getInteractor().getExercise());
                        }

                        startListening();
                    }
                })
                .doOnError(new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e(throwable, "Error while starting exercise streaming");
                        mStarted = false;

                        if (isViewAttached()) {
                            getView().onExerciseError(throwable);
                        }
                    }
                })
                .subscribe()
        );
    }

    protected void startListening() {
        getCompositeDisposable().add(getInteractor().doListenMeasurements()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Measurement>() {
                               @Override
                               public void accept(Measurement measurement) throws Exception {
                                   onReceiveMeasurement(measurement);
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

    protected void onReceiveMeasurement(Measurement measurement) {
        if (mStarted) {
            if (isViewAttached()) {
                getView().addMeasurement(measurement);
            }

            getInteractor().doSaveMeasurement(measurement);
        }
    }
}
