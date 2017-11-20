package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.MeasurementResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.MetricResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Alex on 19/11/2017.
 */

public interface ExerciseService {

    @POST("/api/v1/exercises.json")
    Observable<ExerciseResponse> createExercise(@Body ExerciseRequest exerciseRequest);

    @POST("/api/v1/exercises/{exercise_id}/measurements.json")
    Observable<MeasurementResponse> createMeasurement(@Path("exercise_id") int exerciseId, @Body MeasurementRequest measurementRequest);
}
