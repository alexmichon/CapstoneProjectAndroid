package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiEndPoint;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.MeasurementResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;
import io.reactivex.Single;

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
    public Single<ExerciseResponse> doCreateExercise(ExerciseRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EXERCISES)
                .addHeaders(mApiHeader)
                .addBodyParameter(request)
                .build()
                .getObjectObservable(ExerciseResponse.class)
                .singleOrError();
    }

    @Override
    public Single<MeasurementResponse> doCreateMeasurement(MeasurementRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_MEASUREMENTS)
                .addHeaders(mApiHeader)
                .addPathParameter("exercise_id", String.valueOf(request.getExerciseId()))
                .addBodyParameter(request)
                .build()
                .getObjectObservable(MeasurementResponse.class)
                .singleOrError();
    }
}
