package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.MeasurementResponse;
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
    public Single<ExerciseResponse> doCreateExercise(ExerciseRequest request) {
        final GsonBuilder builder = new GsonBuilder();
        final Gson gson = builder.create();

        JSONObject data = null;
        try {
            data = new JSONObject()
                    .put("id", ID++)
                    .put("exercise_type_id", ID_TEST_EXERCISE);
        } catch (JSONException e) {
            Timber.e(e, "JSON error");
        }
        ExerciseResponse exerciseResponse = gson
                .fromJson(data.toString(),
                        ExerciseResponse.class);
        return Single.just(exerciseResponse);
    }

    @Override
    public Single<MeasurementResponse> doCreateMeasurement(MeasurementRequest request) {
        return Single.never();
    }
}
