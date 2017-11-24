package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.ISensorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseInteractor extends BaseInteractor implements ExerciseContract.Interactor {

    private ISensorService mSensorService;
    private Disposable mNotificationDisposable;

    @Inject
    public ExerciseInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<Exercise> doCreateExercise(final ExerciseType exerciseType) {
        return getDataManager().getApiHelper().getExerciseService().doCreateExercise(new ExerciseRequest(exerciseType))
                .map(new Function<ExerciseResponse, Exercise>() {
                    @Override
                    public Exercise apply(@NonNull ExerciseResponse exerciseResponse) throws Exception {
                        return exerciseResponse.getExercise(exerciseType);
                    }
                });
    }

    @Override
    public Completable doStartExercise(final Exercise exercise) {
        return Completable.create(new CompletableOnSubscribe() {
            @Override
            public void subscribe(@NonNull final CompletableEmitter e) throws Exception {
                mNotificationDisposable = getDataManager().getBluetoothHelper().getExerciseService().startExercise()
                        .subscribe(new Consumer<ISensorService>() {
                            @Override
                            public void accept(ISensorService iSensorService) throws Exception {
                                mSensorService = iSensorService;
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
        return mSensorService.getEncoderObservable().toFlowable(BackpressureStrategy.BUFFER);
    }

    @Override
    public Flowable<Measurement> doListenImu() {
        return mSensorService.getImuObservable().toFlowable(BackpressureStrategy.BUFFER);
    }


    @Override
    public Flowable<Measurement> doListenMeasurements() {
        return Flowable.merge(doListenEncoder(), doListenImu());
    }

    @Override
    public Completable doSaveMeasurement(final Exercise exercise, final Measurement measurement) {
        return Completable.fromSingle(getDataManager().getApiHelper().getExerciseService()
                .doCreateMeasurement(new MeasurementRequest(measurement)));
    }
}
