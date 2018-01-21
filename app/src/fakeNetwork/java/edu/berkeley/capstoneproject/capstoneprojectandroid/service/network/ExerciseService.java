package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import android.content.Context;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.ApplicationContext;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IRxWebSocket;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;


/**
 * Created by Alex on 28/11/2017.
 */

public class ExerciseService implements IExerciseService {

    private final Context mContext;

    @Inject
    public ExerciseService(@ApplicationContext Context context) {
        mContext = context;
    }

    @Override
    public Single<Exercise> doCreateExercise(Exercise.Builder builder) {
        return Single.just(ExerciseFactory.fromBuilder(builder));
    }

    @Override
    public Completable doStopExercise(Exercise exercise) {
        return null;
    }

    @Override
    public Observable<Exercise> doGetExercises() {
        return Observable.never();
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult(Exercise exercise) {
        return Single.never();
    }

    @Override
    public Observable<ExerciseType> doGetExerciseTypes() {
        return Observable.just(
                ExerciseTypeFactory.test()
        );
    }

    @Override
    public IRxWebSocket doStartStreaming(Exercise exercise) {
        return null;
    }

    @Override
    public void doSendMeasurement(IRxWebSocket stream, Measurement measurement) {

    }

    @Override
    public Single<ExerciseGoal> doGetExerciseGoal(Exercise exercise) {
        return null;
    }
}
