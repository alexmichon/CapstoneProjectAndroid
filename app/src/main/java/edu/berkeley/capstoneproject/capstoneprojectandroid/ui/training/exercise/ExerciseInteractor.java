package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import org.reactivestreams.Subscriber;

import java.util.Map;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
    public Single<ExerciseGoal> doCreateExerciseGoal() {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseGoalCreator()
                .flatMap(new Function<ExerciseGoalCreator, SingleSource<? extends ExerciseGoal>>() {
                    @Override
                    public SingleSource<? extends ExerciseGoal> apply(@NonNull ExerciseGoalCreator exerciseGoalCreator) throws Exception {
                        return getDataManager().getApiHelper().getExerciseService().doCreateExerciseGoal(exerciseGoalCreator);
                    }
                });
    }

    @Override
    public Single<Exercise> doCreateExercise() {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseGoalCreator()
                .flatMap(new Function<ExerciseGoalCreator, SingleSource<? extends ExerciseGoal>>() {
                    @Override
                    public SingleSource<? extends ExerciseGoal> apply(@NonNull ExerciseGoalCreator exerciseGoalCreator) throws Exception {
                        return getDataManager().getApiHelper().getExerciseService().doCreateExerciseGoal(exerciseGoalCreator);
                    }
                })
                .flatMap(new Function<ExerciseGoal, SingleSource<? extends ExerciseCreator>>() {
                    @Override
                    public SingleSource<? extends ExerciseCreator> apply(@NonNull final ExerciseGoal exerciseGoal) throws Exception {
                        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseCreator()
                                .flatMap(new Function<ExerciseCreator, SingleSource<? extends ExerciseCreator>>() {
                                    @Override
                                    public SingleSource<? extends ExerciseCreator> apply(@NonNull ExerciseCreator exerciseCreator) throws Exception {
                                        exerciseCreator.setExerciseGoal(exerciseGoal);
                                        return Single.just(exerciseCreator);
                                    }
                                });
                    }
                })
                .flatMap(new Function<ExerciseCreator, SingleSource<? extends Exercise>>() {
                    @Override
                    public SingleSource<? extends Exercise> apply(@NonNull ExerciseCreator exerciseCreator) throws Exception {
                        return getDataManager().getApiHelper().getExerciseService().doCreateExercise(exerciseCreator);
                    }
                })
                .flatMap(new Function<Exercise, SingleSource<? extends Exercise>>() {
                    @Override
                    public SingleSource<? extends Exercise> apply(@NonNull Exercise exercise) throws Exception {
                        return getDataManager().getSessionHelper().getTrainingService().setExercise(exercise).toSingleDefault(exercise);
                    }
                });
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
    public Completable doStopExercise() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                if (mNotificationDisposable != null) {
                    mNotificationDisposable.dispose();
                }
            }
        });
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
