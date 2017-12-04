package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiEndPoint;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.MeasurementResponse;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 20/11/2017.
 */

@Singleton
public class ExerciseService implements IExerciseService {

    private final ApiHeader mApiHeader;

    @Inject
    public ExerciseService(ApiHeader header) {
        mApiHeader = header;
    }

    @Override
    public Single<Exercise> doCreateExercise(final ExerciseType exerciseType) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EXERCISES)
                .addHeaders(mApiHeader)
                .addBodyParameter(new ExerciseRequest(exerciseType))
                .build()
                .getObjectObservable(ExerciseResponse.class)
                .singleOrError()
                .map(new Function<ExerciseResponse, Exercise>() {
                    @Override
                    public Exercise apply(@NonNull ExerciseResponse exerciseResponse) throws Exception {
                        return exerciseResponse.getExercise(exerciseType);
                    }
                });
    }

    @Override
    public Single<Measurement> doSaveMeasurement(final Measurement measurement) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_MEASUREMENTS)
                .addHeaders(mApiHeader)
                .addPathParameter("exercise_id", String.valueOf(measurement.getExercise().getId()))
                .addBodyParameter(new MeasurementRequest(measurement))
                .build()
                .getObjectObservable(MeasurementResponse.class)
                .singleOrError()
                .map(new Function<MeasurementResponse, Measurement>() {
                    @Override
                    public Measurement apply(@NonNull MeasurementResponse measurementResponse) throws Exception {
                        measurement.setId(measurementResponse.getId());
                        return measurement;
                    }
                });
    }

    @Override
    public Single<Measurement> getMaxMeasurement() {
        // TODO
        return Single.never();
    }
}
