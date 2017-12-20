package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import android.content.Context;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResultFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricComparator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoalFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 28/11/2017.
 */

public class ExerciseService implements IExerciseService {

    private final Context mContext;

    @Inject
    public ExerciseService(Context context) {
        mContext = context;
    }

    @Override
    public Single<Exercise> doCreateExercise(ExerciseCreator exerciseCreator) {
        return Single.just(ExerciseFactory.builder()
                .withExerciseType(exerciseCreator.getExerciseType())
                .build()
        );
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
    public Single<ExerciseGoal> doCreateExerciseGoal(ExerciseGoalCreator exerciseGoalCreator) {
        return Single.just(ExerciseGoalFactory.builder()
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
        return Single.just(ExerciseResultFactory.builder()
                .build()
        );
    }

    @Override
    public Observable<ExerciseType> doGetExerciseTypes() {
        return Observable.just(
                ExerciseTypeFactory.test()
        );
    }
}
