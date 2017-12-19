package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import java.util.Map;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService.ENCODER_OBSERVABLE;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.ExerciseService.IMU_OBSERVABLE;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseInteractor extends BaseInteractor implements ExerciseContract.Interactor {

    private Disposable mNotificationDisposable;

    @Inject
    public ExerciseInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<Exercise> doCreateExercise() {
        ExerciseType exerciseType = getDataManager().getSessionHelper().getExerciseService().getCurrentExerciseType();
        return getDataManager().getApiHelper().getExerciseService().doCreateExercise(exerciseType);
    }

    @Override
    public Completable doStartExercise(final Exercise exercise) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull final CompletableEmitter e) throws Exception {
                mNotificationDisposable = getDataManager().getBluetoothHelper().getExerciseService().startExercise()
                        .subscribe(new Consumer<Map<String, Observable<byte[]>>>() {
                            @Override
                            public void accept(Map<String, Observable<byte[]>> map) throws Exception {
                                getDataManager().getBluetoothHelper().getMeasurementService()
                                        .setEncoderObservable(map.get(ENCODER_OBSERVABLE));
                                getDataManager().getBluetoothHelper().getMeasurementService()
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
    public void doStopExercise() {
        if (mNotificationDisposable != null) {
            mNotificationDisposable.dispose();
        }
    }

    @Override
    public Flowable<Measurement> doListenEncoder() {
        return getDataManager().getBluetoothHelper().getMeasurementService()
                .getEncoderObservable().toFlowable(BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<Measurement> doListenImu() {
        return getDataManager().getBluetoothHelper().getMeasurementService()
                .getImuObservable().toFlowable(BackpressureStrategy.BUFFER);
    }


    @Override
    public Flowable<Measurement> doListenMeasurements() {
        return Flowable.merge(doListenEncoder(), doListenImu());
    }

    @Override
    public Completable doSaveMeasurement(final Exercise exercise, final Measurement measurement) {
        return Completable.fromSingle(getDataManager().getApiHelper().getExerciseService()
                .doSaveMeasurement(measurement));
    }
}
