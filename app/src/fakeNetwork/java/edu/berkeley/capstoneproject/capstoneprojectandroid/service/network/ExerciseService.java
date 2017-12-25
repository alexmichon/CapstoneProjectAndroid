package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import io.reactivex.Single;
import timber.log.Timber;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeRepository.ID_TEST_EXERCISE;

/**
 * Created by Alex on 28/11/2017.
 */

public class ExerciseService implements IExerciseService {

    private static int ID = 1;

    @Inject
    public ExerciseService() {

    }

    @Override
    public Single<Exercise> doCreateExercise(ExerciseType exerciseType) {
        Exercise exercise = new Exercise(0, exerciseType);
        exercise.setDuration(10);
        return Single.just(exercise);
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
}
