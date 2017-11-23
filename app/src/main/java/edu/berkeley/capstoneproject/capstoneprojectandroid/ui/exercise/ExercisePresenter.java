package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import android.util.Log;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExercisePresenter<V extends ExerciseContract.View, I extends ExerciseContract.Interactor>
    extends BasePresenter<V, I> implements ExerciseContract.Presenter<V, I> {

    private static final String TAG = ExercisePresenter.class.getSimpleName();

    private boolean mStarted = false;

    private ExerciseType mExerciseType;
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
    public void onAttach(V view, ExerciseType exerciseType) {
        super.onAttach(view);
        mExerciseType = exerciseType;
        createExercise();
    }

    protected void createExercise() {
        getView().showLoading("Wait...", false);
        getCompositeDisposable().add(getInteractor().doCreateExercise(mExerciseType)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Exercise>() {
                    @Override
                    public void accept(Exercise exercise) throws Exception {
                        mExercise = exercise;
                        getView().hideLoading();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        getView().showError(throwable);
                    }
                })
        );
    }


    @Override
    public void onStartClick() {
        getView().onWaitToStart();

        getCompositeDisposable().add(getInteractor().doStartExercise(mExercise)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        getView().onExerciseStart();
                        mStarted = true;
                        startListening();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Timber.e("Error while starting exercise", throwable);
                        getView().showError(throwable);
                        mStarted = false;
                    }
                })
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
                                getView().showError(throwable);
                            }
                        })
        );
    }

    @Override
    public void onStopClick() {
        mStarted = false;
        getInteractor().doStopExercise();
        getView().onExerciseStop();
    }

    @Override
    public void onPause() {
        mStarted = false;
        getInteractor().doStopExercise();
        getView().onExerciseStop();
    }

    protected void onReceiveMeasurement(Measurement measurement) {
        mExercise.addMeasurement(measurement);
        getView().addMeasurement(measurement);
        getCompositeDisposable().add(getInteractor().doSaveMeasurement(mExercise, measurement)
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
