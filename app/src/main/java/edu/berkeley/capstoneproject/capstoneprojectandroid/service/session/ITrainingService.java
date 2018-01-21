package edu.berkeley.capstoneproject.capstoneprojectandroid.service.session;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by Alex on 20/12/2017.
 */

public interface ITrainingService {

    Single<Exercise> getExercise();
    Completable setExercise(Exercise exercise);


}
