package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import org.reactivestreams.Subscriber;

import java.util.Map;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.IMeasurementManager;
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

    private final IExerciseManager mExerciseManager;
    private final IMeasurementManager mMeasurementManager;

    @Inject
    public ExerciseInteractor(IExerciseManager exerciseManager, IMeasurementManager measurementManager) {
        mExerciseManager = exerciseManager;
        mMeasurementManager = measurementManager;
    }

    /*@Override
    public Single<ExerciseGoal> doCreateExerciseGoal() {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseGoalCreator()
                .flatMap(new Function<ExerciseGoalCreator, SingleSource<? extends ExerciseGoal>>() {
                    @Override
                    public SingleSource<? extends ExerciseGoal> apply(@NonNull final ExerciseGoalCreator exerciseGoalCreator) throws Exception {
                        return getDataManager().getSessionHelper().getTrainingService().getExercise()
                                .flatMap(new Function<Exercise, SingleSource<? extends ExerciseGoal>>() {
                                    @Override
                                    public SingleSource<? extends ExerciseGoal> apply(@NonNull Exercise exercise) throws Exception {
                                        return getDataManager().getApiHelper().getExerciseService().doCreateExerciseGoal(exercise, exerciseGoalCreator);
                                    }
                                });
                    }
                });
    }*/


    /*@Override
    public Single<Exercise> doCreateExercise() {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseCreator()
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
    }*/

    @Override
    public Exercise getExercise() {
        return mExerciseManager.getCurrentExercise();
    }

    @Override
    public Completable doStartExercise() {
        return mExerciseManager.start();
    }

    @Override
    public Completable doStopExercise() {
        return mExerciseManager.stop();
    }


    @Override
    public Flowable<Measurement> doListenMeasurements() {
        return mExerciseManager.listen();
    }

    @Override
    public Completable doSaveMeasurement(final Exercise exercise, final Measurement measurement) {
        return mMeasurementManager.save(measurement);
    }
}
