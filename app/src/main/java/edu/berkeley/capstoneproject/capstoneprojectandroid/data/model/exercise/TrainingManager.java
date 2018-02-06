package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.bytes.BytesDecoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IRxWebSocket;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService.ENCODER_OBSERVABLE;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService.IMU_OBSERVABLE;

/**
 * Created by Alex on 30/12/2017.
 */

@Singleton
public class TrainingManager implements ITrainingManager {

    private final IApiHelper mApiHelper;
    private final IBluetoothHelper mBluetoothHelper;
    private final BytesDecoder mBytesDecoder;

    private Exercise mExercise;
    private ExerciseType mExerciseType;

    private Subscription mListenSubscription;
    private Subscription mImuSubscription;

    private IRxWebSocket mExerciseStream;

    @Inject
    public TrainingManager(IApiHelper apiHelper, IBluetoothHelper bluetoothHelper, BytesDecoder decoder) {
        mApiHelper = apiHelper;
        mBluetoothHelper = bluetoothHelper;
        mBytesDecoder = decoder;
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
    public ExerciseType getExerciseType() {
        return mExerciseType;
    }

    @Override
    public void setExerciseType(ExerciseType exerciseType) {
        mExerciseType = exerciseType;
    }

    @Override
    public Completable doStartSensors() {
        return mBluetoothHelper.getExerciseService().doStartExercise()
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
    public Completable doStopSensors() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if (mListenSubscription != null) {
                    mListenSubscription.cancel();
                    mListenSubscription = null;
                }
            }
        });
    }

    @Override
    public Completable doStopExercise() {
        return mApiHelper.getExerciseService().doStopExercise(mExercise);
    }

    @Override
    public Flowable<Measurement> doListen() {
        return Flowable.merge(listenEncoder(), listenImu())
                .doOnSubscribe(new Consumer<Subscription>() {
                    @Override
                    public void accept(Subscription subscription) throws Exception {
                        mListenSubscription = subscription;
                    }
                });
    }

    @Override
    public boolean isListening() {
        return mListenSubscription != null;
    }

    private Flowable<Measurement> listenEncoder() {
        return mBluetoothHelper.getMeasurementService()
                .getEncoderObservable().toFlowable(BackpressureStrategy.BUFFER)
                .flatMap(new Function<byte[], Publisher<Measurement>>() {
                    @Override
                    public Publisher<Measurement> apply(byte[] bytes) throws Exception {
                        return Flowable.fromIterable(mBytesDecoder.decodeEncoder(mExercise, bytes));
                    }
                });
    }

    private Flowable<Measurement> listenImu() {
        return mBluetoothHelper.getMeasurementService()
                .getImuObservable().toFlowable(BackpressureStrategy.BUFFER)
                .flatMap(new Function<byte[], Publisher<Measurement>>() {
                    @Override
                    public Publisher<Measurement> apply(byte[] bytes) throws Exception {
                        return Flowable.fromIterable(mBytesDecoder.decodeImu(mExercise, bytes));
                    }
                });
    }

    @Override
    public Completable doStartStreaming() {
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

    @Override
    public Single<ExerciseGoal> doGetExerciseGoal() {
        return mApiHelper.getExerciseService().doGetExerciseGoal(mExercise);
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult() {
        return mApiHelper.getExerciseService().doGetExerciseResult(mExercise);
    }
}
