package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import android.util.Log;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.EncoderMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.ImuMeasurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement.LABEL_ACC;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement.LABEL_GYR;

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
    public void onStartClick() {
        mExercise.start();
        getCompositeDisposable().add(getInteractor().doStartExercise()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer() {
                    @Override
                    public void accept(Object o) throws Exception {
                        getView().showMessage("Let's go !");
                        getView().onExerciseStart();
                        mStarted = true;
                        startListening();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "Error while starting exercise", throwable);
                        getView().showMessage("An error occurred");
                        mStarted = false;
                    }
                })
        );
    }

    private void startListening() {
        getCompositeDisposable().add(getInteractor().doListenEncoder()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableObserver<Measurement>() {
                    @Override
                    public void onNext(@NonNull Measurement measurement) {
                        getView().addEncoderValue(measurement);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );

        getCompositeDisposable().add(getInteractor().doListenImu()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribeWith(new DisposableObserver<Measurement>() {
                    @Override
                    public void onNext(@NonNull Measurement measurement) {
                        if (measurement.getLabel().equals(LABEL_ACC)) {
                            getView().addAccMeasurement(measurement);
                        }
                        else if (measurement.getLabel().equals(LABEL_GYR)) {
                            getView().addGyrMeasurement(measurement);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                })
        );
    }

    @Override
    public void onStopClick() {
        mStarted = false;
        mExercise.stop();
        getInteractor().doStopExercise();
        getView().onExerciseStop();
    }

    @Override
    public void setExerciseType(ExerciseType exerciseType) {
        mExercise = new Exercise(exerciseType);
    }

    @Override
    public void onPause() {
        mStarted = false;
        mExercise.stop();
        getInteractor().doStopExercise();
    }
}
