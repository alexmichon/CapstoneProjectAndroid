package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import android.content.Context;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResultFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

import edu.berkeley.capstoneproject.capstoneprojectandroid.di.qualifier.ApplicationContext;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.ExerciseStream;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IExerciseStream;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IStream;
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
        return Completable.complete();
    }

    @Override
    public Observable<Exercise> doGetExercises() {
        return Observable.never();
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult(Exercise exercise) {
        return Single.just(ExerciseResultFactory.fromExerciseGoal(exercise.getExerciseGoal()));
    }

    @Override
    public Observable<ExerciseType> doGetExerciseTypes() {
        return Observable.just(
                ExerciseTypeFactory.create()
        );
    }

    @Override
    public Single<ExerciseGoal> doGetExerciseGoal(Exercise exercise) {
        return Single.just(
                ExerciseGoalFactory.create(exercise)
        );
    }





    @Override
    public IExerciseStream getExerciseStreaming(Exercise exercise) {
        return new ExerciseStream(exercise);
    }

}