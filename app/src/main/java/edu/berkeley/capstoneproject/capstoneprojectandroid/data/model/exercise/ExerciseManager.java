package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import org.reactivestreams.Publisher;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IRxWebSocket;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService.ENCODER_OBSERVABLE;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService.IMU_OBSERVABLE;

/**
 * Created by Alex on 30/12/2017.
 */

@Singleton
public class ExerciseManager implements IExerciseManager {

    private final IApiHelper mApiHelper;
    private final IBluetoothHelper mBluetoothHelper;

    private Exercise mExercise;
    private Disposable mExerciseDisposable;
    private IRxWebSocket mExerciseStream;

    @Inject
    public ExerciseManager(IApiHelper apiHelper, IBluetoothHelper bluetoothHelper) {
        mApiHelper = apiHelper;
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
    public Completable doStartSensors() {
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
                })
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mExerciseDisposable = disposable;
                    }
                });
    }

    @Override
    public Completable doStopSensors() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if (mExerciseDisposable != null) {
                    mExerciseDisposable.dispose();
                }
            }
        });
    }

    @Override
    public Flowable<Measurement> listen() {
        return Flowable.merge(listenEncoder(), listenImu());
    }

    @Override
    public Completable doSave() {
        //return mApiHelper.getExerciseService().doSaveMeasurements(mExercise);
//        List<Completable> completableList = new ArrayList<>();
//        for (MetricMeasurementList metricMeasurementList: mExercise.getMetricMeasurementLists()) {
//            for (Measurement measurement: metricMeasurementList.getMeasurements()) {
//                completableList.add(mApiHelper.getExerciseService().doSaveMeasurement(measurement).toCompletable());
//            }
//        }
//        return Completable.concat(completableList);
        return Completable.complete();
    }

    private Flowable<Measurement> listenEncoder() {
        return mBluetoothHelper.getMeasurementService()
                .getEncoderObservable().toFlowable(BackpressureStrategy.BUFFER)
                .flatMap(new Function<byte[], Publisher<Measurement>>() {
                    @Override
                    public Publisher<Measurement> apply(byte[] bytes) throws Exception {
                        return Flowable.fromIterable(Measurement.decodeEncoder(mExercise, bytes));
                    }
                });
    }

    private Flowable<Measurement> listenImu() {
        return mBluetoothHelper.getMeasurementService()
                .getImuObservable().toFlowable(BackpressureStrategy.BUFFER)
                .flatMap(new Function<byte[], Publisher<Measurement>>() {
                    @Override
                    public Publisher<Measurement> apply(byte[] bytes) throws Exception {
                        return Flowable.fromIterable(Measurement.decodeImu(mExercise, bytes));
                    }
                });
    }

    @Override
    public Completable doStartExerciseStream() {
        mExerciseStream = mApiHelper.getExerciseService().doStartStreaming(mExercise);
        return mExerciseStream.connect();
    }

    @Override
    public Completable doStopStreaming() {
        return mExerciseStream.disconnect();
    }

    @Override
    public void doSendMeasurement(final Measurement measurement) {
        if (mExerciseStream != null) {
            mApiHelper.getExerciseService().doSendMeasurement(mExerciseStream, measurement);
        }
    }
}
