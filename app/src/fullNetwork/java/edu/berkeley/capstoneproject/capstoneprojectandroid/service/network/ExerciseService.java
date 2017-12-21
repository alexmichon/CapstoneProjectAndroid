package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiEndPoint;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseGoalRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseGoalResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseTypeResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.MeasurementResponse;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
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
    public Single<Exercise> doCreateExercise(final ExerciseCreator exerciseCreator) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EXERCISES)
                .addHeaders(mApiHeader)
                .addBodyParameter(new ExerciseRequest(exerciseCreator))
                .build()
                .getObjectObservable(ExerciseResponse.class)
                .singleOrError()
                .map(new Function<ExerciseResponse, Exercise>() {
                    @Override
                    public Exercise apply(@NonNull ExerciseResponse exerciseResponse) throws Exception {
                        return exerciseResponse.getExercise(exerciseCreator);
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
    public Single<ExerciseGoal> doCreateExerciseGoal(Exercise exercise, ExerciseGoalCreator exerciseGoalCreator) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EXERCISE_GOAL)
                .addHeaders(mApiHeader)
                .addPathParameter("exercise_id", String.valueOf(exercise.getId()))
                .addBodyParameter(new ExerciseGoalRequest(exerciseGoalCreator))
                .build()
                .getObjectObservable(ExerciseGoalResponse.class)
                .singleOrError()
                .map(new Function<ExerciseGoalResponse, ExerciseGoal>() {
                    @Override
                    public ExerciseGoal apply(@NonNull ExerciseGoalResponse exerciseGoalResponse) throws Exception {
                        return null;
                    }
                });
    }

    @Override
    public Single<ExerciseGoalCreator> doGetDefaultExerciseGoal(ExerciseType exerciseType) {
        return null;
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult(Exercise exercise) {
        return null;
    }

    @Override
    public Observable<ExerciseType> doGetExerciseTypes() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXERCISE_TYPES)
                .addHeaders(mApiHeader)
                .build()
                .getObjectListObservable(ExerciseTypeResponse.class)
                .flatMap(new Function<List<ExerciseTypeResponse>, ObservableSource<ExerciseTypeResponse>>() {
                    @Override
                    public ObservableSource<ExerciseTypeResponse> apply(@NonNull List<ExerciseTypeResponse> exerciseTypeResponses) throws Exception {
                        return Observable.fromIterable(exerciseTypeResponses);
                    }
                })
                .map(new Function<ExerciseTypeResponse, ExerciseType>() {
                    @Override
                    public ExerciseType apply(@NonNull ExerciseTypeResponse exerciseTypeResponse) throws Exception {
                        return exerciseTypeResponse.getExerciseType();
                    }
                });
    }
}
