package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.ExerciseFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.MeasurementFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;
import io.reactivex.Observable;
import io.reactivex.Single;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_X;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Y;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Accelerometer.ID_ACC_Z;

/**
 * Created by Alex on 28/11/2017.
 */

public class ExerciseService implements IExerciseService {

    @Inject
    public ExerciseService() {

    }

    @Override
    public Single<Exercise> doCreateExercise(ExerciseType exerciseType) {
        return Single.just(ExerciseFactory.builder()
                .withExerciseType(exerciseType)
                .build());
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
    public Observable<Exercise> getExercises() {
        return Observable.just(ExerciseFactory.builder()
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
}
