package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService.ENCODER_OBSERVABLE;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService.IMU_OBSERVABLE;

/**
 * Created by Alex on 30/12/2017.
 */

@Singleton
public class ExerciseManager implements IExerciseManager {

    private final IBluetoothHelper mBluetoothHelper;

    private Exercise mExercise;
    private Disposable mExerciseDisposable;

    @Inject
    public ExerciseManager(IBluetoothHelper bluetoothHelper) {
        mBluetoothHelper = bluetoothHelper;
    }

    @Override
    public Exercise getCurrentExercise() {
        return mExercise;
    }

    @Override
    public void setCurrentExercise(Exercise exercise) {
        mExercise = exercise;
    }

    @Override
    public Completable start() {
        return mBluetoothHelper.getExerciseService().startExercise()
                .flatMapCompletable(new Function<Map<String,Observable<byte[]>>, CompletableSource>() {
                    @Override
                    public CompletableSource apply(final Map<String, Observable<byte[]>> stringObservableMap) throws Exception {
                        return Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                mBluetoothHelper.getMeasurementService()
                                        .setEncoderObservable(stringObservableMap.get(ENCODER_OBSERVABLE));
                                mBluetoothHelper.getMeasurementService()
                                        .setImuObservable(stringObservableMap.get(IMU_OBSERVABLE));
                            }
                        });
                    }
                });
    }

    @Override
    public Completable stop() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExerciseDisposable.dispose();
            }
        });
    }

    @Override
    public Flowable<Measurement> listen() {
        return Flowable.merge(listenEncoder(), listenImu());
    }

    private Flowable<Measurement> listenEncoder() {
        return mBluetoothHelper.getMeasurementService()
                .getEncoderObservable().toFlowable(BackpressureStrategy.BUFFER)
                .map(new Function<Measurement, Measurement>() {
                    @Override
                    public Measurement apply(Measurement measurement) throws Exception {
                        measurement.setExercise(mExercise);
                        return measurement;
                    }
                });
    }

    private Flowable<Measurement> listenImu() {
        return mBluetoothHelper.getMeasurementService()
                .getImuObservable().toFlowable(BackpressureStrategy.BUFFER)
                .map(new Function<Measurement, Measurement>() {
                    @Override
                    public Measurement apply(Measurement measurement) throws Exception {
                        measurement.setExercise(mExercise);
                        return measurement;
                    }
                });
    }
}
