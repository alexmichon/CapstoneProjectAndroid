package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseCreator;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalCreator;
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
        mExerciseCreator = new ExerciseCreator();
        mExerciseGoalCreator = new ExerciseGoalCreator();
    }

    public Single<ExerciseCreator> getExerciseCreator() {
        return Single.just(mExerciseCreator);
    }

    @Override
    public Completable resetExerciseCreator() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExerciseCreator = new ExerciseCreator();
            }
        });
    }

    public Single<ExerciseGoalCreator> getExerciseGoalCreator() {
        return Single.just(mExerciseGoalCreator);
    }

    @Override
    public Completable resetExerciseGoalCreator() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExerciseGoalCreator = new ExerciseGoalCreator();
            }
        });
    }
}
