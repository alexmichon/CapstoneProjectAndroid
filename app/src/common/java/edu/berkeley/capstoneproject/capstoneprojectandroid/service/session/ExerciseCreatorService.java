package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;

/**
 * Created by Alex on 17/12/2017.
 */

@Singleton
public class ExerciseCreatorService implements IExerciseCreatorService {

    private ExerciseCreator mExerciseCreator;
    private ExerciseGoalCreator mExerciseGoalCreator;

    @Inject
    public ExerciseCreatorService() {
    }

    public Single<ExerciseCreator> getExerciseCreator() {
        return Single.just(mExerciseCreator);
    }

    @Override
    public Completable newExerciseCreator(final ExerciseType exerciseType) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExerciseCreator = new ExerciseCreator(exerciseType);
            }
        });
    }

    public Single<ExerciseGoalCreator> getExerciseGoalCreator() {
        return Single.just(mExerciseGoalCreator);
    }

    @Override
    public Completable newExerciseGoalCreator(final ExerciseType exerciseType) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExerciseGoalCreator = new ExerciseGoalCreator();
            }
        });
    }

    @Override
    public Completable clear() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExerciseCreator = null;
                mExerciseGoalCreator = null;
            }
        });
    }
}
