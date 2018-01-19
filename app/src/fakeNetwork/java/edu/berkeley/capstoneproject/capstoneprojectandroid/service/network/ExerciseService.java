package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

<<<<<<< HEAD
import java.util.Date;
=======
import android.content.Context;

import java.util.Random;
>>>>>>> feature/am_exercise-goal

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
<<<<<<< HEAD
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.ExerciseFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.MeasurementFactory;
=======
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeFactory;
>>>>>>> feature/am_exercise-goal
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResultFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
<<<<<<< HEAD
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;
import io.reactivex.Observable;
import io.reactivex.Single;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_X;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Y;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Z;
=======
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricComparator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoalFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;
import io.reactivex.Observable;
import io.reactivex.Single;
>>>>>>> feature/am_exercise-goal

/**
 * Created by Alex on 28/11/2017.
 */

public class ExerciseService implements IExerciseService {

<<<<<<< HEAD
=======
    private final Context mContext;

>>>>>>> feature/am_exercise-goal
    @Inject
    public ExerciseService(Context context) {
        mContext = context;
    }

    @Override
<<<<<<< HEAD
    public Single<Exercise> doCreateExercise(ExerciseType exerciseType) {
        return Single.just(ExerciseFactory.builder()
                .withExerciseType(exerciseType)
                .build());
=======
    public Single<Exercise> doCreateExercise(ExerciseCreator exerciseCreator) {
        return Single.just(ExerciseFactory.builder()
                .withExerciseType(exerciseCreator.getExerciseType())
                .withExerciseGoal(exerciseCreator.getExerciseGoal())
                .build()
        );
>>>>>>> feature/am_exercise-goal
    }

    @Override
    public Single<Measurement> doSaveMeasurement(Measurement measurement) {
        measurement.setId(0);
        return Single.just(measurement);
    }

    @Override
    public Single<Measurement> getMaxMeasurement() {
        return Single.never();
    }

    @Override
<<<<<<< HEAD
    public Observable<Exercise> getExercises() {
        return Observable.just(ExerciseFactory.builder()
                .withStartDate(new Date())
                .withDuration(120)
                .withMeasurements(
                        MeasurementFactory.listBuilder(100)
                                .withinRange(-1, 1)
                                .withMetric(SensorManager.find(SensorManager.ID_ACCELEROMETER).getMetric(ID_ACC_X))
                                .withSampleRate(100)
                                .build()
                )
                .withMeasurements(
                        MeasurementFactory.listBuilder(100)
                                .withinRange(-1, 1)
                                .withMetric(SensorManager.find(SensorManager.ID_ACCELEROMETER).getMetric(ID_ACC_Y))
                                .withSampleRate(100)
                                .build()
                )
                .withMeasurements(
                        MeasurementFactory.listBuilder(100)
                                .withinRange(-1, 1)
                                .withMetric(SensorManager.find(SensorManager.ID_ACCELEROMETER).getMetric(ID_ACC_Z))
                                .withSampleRate(100)
                                .build()
                )
                .build()
        );
    }
=======
    public Single<ExerciseGoal> doCreateExerciseGoal(ExerciseGoalCreator exerciseGoalCreator) {
        return Single.just(ExerciseGoalFactory.builder()
                .withExerciseType(exerciseGoalCreator.getExerciseType())
                .withType(exerciseGoalCreator.getType())
                .withMetricGoals(exerciseGoalCreator.getMetricGoals())
                .build()
        );
    }

    @Override
    public Single<ExerciseGoalCreator> doGetDefaultExerciseGoal(ExerciseType exerciseType) {
        return Single.just(ExerciseGoalFactory.builder()
                .withType(ExerciseGoal.Type.DEFAULT)
                .addMetricGoal(MetricGoalFactory.builder()
                        .withMetric(SensorManager.find(SensorManager.ID_ACCELEROMETER).getMetric(Accelerometer.ID_ACC_X))
                        .withGoal(1.0f)
                        .withType(MetricGoal.Type.MAX)
                        .withComparator(new MetricComparator(MetricComparator.Type.GT))
                        .build()
                )
                .creator()
        );
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult(Exercise exercise) {
        ExerciseResultFactory.Builder builder = ExerciseResultFactory.builder();
        for (MetricGoal metricGoal: exercise.getExerciseGoal().getMetricGoals()) {
            builder.addMetricResult(new MetricResult(metricGoal, new Random().nextFloat(), true));
        }
        return Single.just(builder.build());
    }

    @Override
    public Observable<ExerciseType> doGetExerciseTypes() {
        return Observable.just(
                ExerciseTypeFactory.test()
        );
    }
>>>>>>> feature/am_exercise-goal
}
