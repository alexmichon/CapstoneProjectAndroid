package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import android.support.annotation.NonNull;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.bytes.BytesDecoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IExerciseStream;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IStream;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
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

    private IExerciseStream mExerciseStream;

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
/*        return mBluetoothHelper.getExerciseService().doStartExercise()
                .singleOrError()
                .flatMapCompletable(new Function<Map<String, Observable<byte[]>>, CompletableSource>() {
                    @Override
                    public CompletableSource apply(final Map<String, Observable<byte[]>> observableMap) throws Exception {
                        return Completable.fromAction(new Action() {
                            @Override
                            public void run() throws Exception {
                                mBluetoothHelper.getMeasurementService()
                                        .setImuObservable(observableMap.get(IMU_OBSERVABLE));
                            }
                        });
                    }
                });*/

        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull final CompletableEmitter e) throws Exception {
                mBluetoothHelper.getExerciseService().doStartExercise()
                        .subscribe(new Consumer<Map<String, Observable<byte[]>>>() {
                            @Override
                            public void accept(Map<String, Observable<byte[]>> map) throws Exception {
                                //mBluetoothHelper.getMeasurementService()
                                //        .setEncoderObservable(map.get(ENCODER_OBSERVABLE));
                                mBluetoothHelper.getMeasurementService()
                                        .setImuObservable(map.get(IMU_OBSERVABLE));
                                e.onComplete();
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                e.onError(throwable);
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
        return listenImu()
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
        mExerciseStream = mApiHelper.getExerciseService().getExerciseStreaming(mExercise);
        return mExerciseStream.connect();
    }

    @Override
    public Completable doStopStreaming() {
        return mExerciseStream.disconnect();
    }

    @Override
    public void doSendMeasurement(final Measurement measurement) {
        if (mExerciseStream != null) {
            mExerciseStream.doSendMeasurement(measurement);
        }
    }

    @Override
    public Single<ExerciseGoal> doGetExerciseGoal() {
        return mApiHelper.getExerciseService().doGetExerciseGoal(mExercise)
                .doOnSuccess(new Consumer<ExerciseGoal>() {
                    @Override
                    public void accept(ExerciseGoal exerciseGoal) throws Exception {
                        mExercise.setExerciseGoal(exerciseGoal);
                    }
                });
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult() {
        return mApiHelper.getExerciseService().doGetExerciseResult(mExercise);
    }
}
