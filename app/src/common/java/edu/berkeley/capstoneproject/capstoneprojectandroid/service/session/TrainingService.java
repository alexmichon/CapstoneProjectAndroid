package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.functions.Action;

/**
 * Created by Alex on 20/12/2017.
 */

@Singleton
public class TrainingService implements ITrainingService {

    private Exercise mExercise;

    @Inject
    public TrainingService() {

    }

    @Override
    public Single<Exercise> getExercise() {
        return Single.just(mExercise);
    }

    @Override
    public Completable setExercise(final Exercise exercise) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mExercise = exercise;
            }
        });
    }
}
