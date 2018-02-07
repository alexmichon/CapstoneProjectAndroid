package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import com.rx2androidnetworking.Rx2AndroidNetworking;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiEndPoint;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseGoalResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseResultResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ExerciseTypeResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.MeasurementRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.ObjectRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.ExerciseStream;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IExerciseStream;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IStream;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants.ApiConstants;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
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
    public Single<Exercise> doCreateExercise(Exercise.Builder builder) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EXERCISES)
                .addJSONObjectBody(new ObjectRequest<>("exercise",  new ExerciseRequest(builder)).toJson())
                .build()
                .getObjectObservable(ExerciseResponse.class)
                .singleOrError()
                .map(new Function<ExerciseResponse, Exercise>() {
                    @Override
                    public Exercise apply(@NonNull ExerciseResponse exerciseResponse) throws Exception {
                        return exerciseResponse.get();
                    }
                });
    }

    @Override
    public Completable doStopExercise(Exercise exercise) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_EXERCISE_STOP)
                .addPathParameter("exercise_id", String.valueOf(exercise.getId()))
                .build()
                .getObjectObservable(ExerciseResponse.class)
                .singleOrError()
                .toCompletable();
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
    public Single<ExerciseGoal> doGetExerciseGoal(Exercise exercise) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXERCISE_GOAL)
                .addPathParameter("exercise_id", String.valueOf(exercise.getId()))
                .build()
                .getObjectObservable(ExerciseGoalResponse.class)
                .singleOrError()
                .map(new Function<ExerciseGoalResponse, ExerciseGoal>() {
                    @Override
                    public ExerciseGoal apply(ExerciseGoalResponse exerciseGoalResponse) throws Exception {
                        return exerciseGoalResponse.get();
                    }
                });
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult(Exercise exercise) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXERCISE_RESULT)
                .addPathParameter("exercise_id", String.valueOf(exercise.getId()))
                .build()
                .getObjectObservable(ExerciseResultResponse.class)
                .singleOrError()
                .map(new Function<ExerciseResultResponse, ExerciseResult>() {
                    @Override
                    public ExerciseResult apply(ExerciseResultResponse exerciseResultResponse) throws Exception {
                        return exerciseResultResponse.get();
                    }
                });
    }

    @Override
    public Observable<Exercise> doGetExercises() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_EXERCISES)
                .build()
                .getObjectListObservable(ExerciseResponse.class)
                .flatMap(new Function<List<ExerciseResponse>, ObservableSource<Exercise>>() {
                    @Override
                    public ObservableSource<Exercise> apply(final List<ExerciseResponse> exerciseResponses) throws Exception {
                        return Observable.create(new ObservableOnSubscribe<Exercise>() {
                            @Override
                            public void subscribe(ObservableEmitter<Exercise> e) throws Exception {
                                for (ExerciseResponse exerciseResponse: exerciseResponses) {
                                    e.onNext(exerciseResponse.get());
                                }
                                e.onComplete();
                            }
                        });
                    }
                });
    }





    @Override
    public IExerciseStream getExerciseStreaming(final Exercise exercise) {
        // TODO Move to other network service
        return new ExerciseStream(getOkHttpClient(), new Request.Builder()
                .url(ApiConstants.WEBSOCKET_URL + "?exercise_id=" + exercise.getId())
                .build(),
                exercise);
    }
}
