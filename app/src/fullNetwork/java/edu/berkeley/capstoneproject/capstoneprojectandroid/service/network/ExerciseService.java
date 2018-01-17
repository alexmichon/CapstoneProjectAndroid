package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.net.URL;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiEndPoint;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseResultResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseTypeResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.MeasurementResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ObjectRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ObjectsRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.SaveMeasurementsRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IRxWebSocket;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.ApiConstants;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Alex on 20/11/2017.
 */

@Singleton
public class ExerciseService extends NetworkService implements IExerciseService {

    @Inject
    public ExerciseService(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }

    @Override
    public Single<Exercise> doCreateExercise(final Exercise.Builder builder) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EXERCISES)
                .addJSONObjectBody(new ObjectRequest<>("exercise",  new ExerciseRequest(builder)).toJson())
                .build()
                .getObjectObservable(ExerciseResponse.class)
                .singleOrError()
                .map(new Function<ExerciseResponse, Exercise>() {
                    @Override
                    public Exercise apply(@NonNull ExerciseResponse exerciseResponse) throws Exception {
                        return exerciseResponse.getExercise(builder);
                    }
                });
    }

    @Override
    public Completable doSaveMeasurements(final Exercise exercise) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_SAVE_MEASUREMENTS)
                .addPathParameter("exercise_id", String.valueOf(exercise.getId()))
                .addJSONObjectBody(new ObjectsRequest<>("measurements", new SaveMeasurementsRequest(exercise)).toJson())
                .build()
                .getJSONArrayObservable()
                .singleOrError()
                .toCompletable();
    }

    @Override
    public Single<Measurement> doSaveMeasurement(final Measurement measurement) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_MEASUREMENTS)
                .addPathParameter("exercise_id", String.valueOf(measurement.getExercise().getId()))
                .addJSONObjectBody(new ObjectRequest<>("measurement", new MeasurementRequest(measurement)).toJson())
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

    /*@Override
    public Single<ExerciseGoal> doCreateExerciseGoal(Exercise exercise, ExerciseGoalCreator exerciseGoalCreator) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EXERCISE_GOAL)
                .addPathParameter("exercise_id", String.valueOf(exercise.getId()))
                .addBodyParameter(new ExerciseGoalRequest(exerciseGoalCreator))
                .doCreate()
                .getObjectObservable(ExerciseGoalResponse.class)
                .singleOrError()
                .map(new Function<ExerciseGoalResponse, ExerciseGoal>() {
                    @Override
                    public ExerciseGoal apply(@NonNull ExerciseGoalResponse exerciseGoalResponse) throws Exception {
                        return null;
                    }
                });
    }*/

    /*@Override
    public Single<ExerciseGoalCreator> doGetDefaultExerciseGoal(ExerciseType exerciseType) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXERCISE_TYPE_DEFAULT_GOAL)
                .addPathParameter("exercise_type_id", String.valueOf(exerciseType.getId()))
                .doCreate()
                .getObjectObservable(ExerciseGoalResponse.class)
                .singleOrError()
                .map(new Function<ExerciseGoalResponse, ExerciseGoalCreator>() {
                    @Override
                    public ExerciseGoalCreator apply(@NonNull ExerciseGoalResponse exerciseGoalResponse) throws Exception {
                        return exerciseGoalResponse.get();
                    }
                });
    }*/

    @Override
    public Single<ExerciseResult> doGetExerciseResult(Exercise exercise) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXERCISE_RESULT)
                .addPathParameter("exercise_id", String.valueOf(exercise.getId()))
                .build()
                .getObjectObservable(ExerciseResultResponse.class)
                .singleOrError()
                .map(new Function<ExerciseResultResponse, ExerciseResult>() {
                    @Override
                    public ExerciseResult apply(@NonNull ExerciseResultResponse exerciseResultResponse) throws Exception {
                        return exerciseResultResponse.get();
                    }
                });
    }

    @Override
    public Observable<ExerciseType> doGetExerciseTypes() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXERCISE_TYPES)
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

    @Override
    public IRxWebSocket doStartStreaming(final Exercise exercise) {
        return new RxActionCable(getOkHttpClient(), new Request.Builder()
                .url(ApiConstants.WEBSOCKET_URL + "?exercise_id=" + exercise.getId())
                .build(), "ExerciseChannel" , "\"exercise_id\":" + exercise.getId());
    }

    @Override
    public void doSendMeasurement(IRxWebSocket stream, final Measurement measurement) {
        stream.send(new MeasurementRequest(measurement).toJson().toString());
    }
}
