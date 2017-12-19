package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;

/**
 * Created by Alex on 17/12/2017.
 */

@Singleton
public class ExerciseService implements IExerciseService {

    private Exercise mExercise;
    private ExerciseType mExerciseType;
    private ExerciseGoal mExerciseGoal;

    @Inject
    public ExerciseService() {

    }

    @Override
    public Single<Exercise> getCurrentExercise() {
        return Single.just(mExercise);
    }

    @Override
    public Completable setCurrentExercise(final Exercise exercise) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExercise = exercise;
            }
        });
    }

    @Override
    public Single<ExerciseType> getCurrentExerciseType() {
        return Single.just(mExerciseType);
    }

    @Override
    public Completable setCurrentExerciseType(final ExerciseType exerciseType) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExerciseType = exerciseType;
            }
        });
    }

    @Override
    public Single<ExerciseGoal> getCurrentExerciseGoal() {
        return Single.just(mExerciseGoal);
    }

    @Override
    public Completable setCurrentExerciseGoal(final ExerciseGoal exerciseGoal) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExerciseGoal = exerciseGoal;
            }
        });
    }

    @Override
    public Completable clear() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExercise = null;
                mExerciseGoal = null;
                mExerciseType = null;
            }
        });
    }
}
