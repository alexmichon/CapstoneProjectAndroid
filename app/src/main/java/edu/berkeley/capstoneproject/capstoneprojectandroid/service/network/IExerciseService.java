package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.stream.IRxWebSocket;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 20/11/2017.
 */

public interface IExerciseService {

    Single<Exercise> doCreateExercise(Exercise.Builder builder);
    Completable doStopExercise(Exercise exercise);

    //Single<ExerciseGoal> doCreateExerciseGoal(Exercise exercise, Exercise.Builder builder);
    //Single<ExerciseGoalCreator> doGetExerciseGoal(ExerciseType exerciseType);

    Single<ExerciseResult> doGetExerciseResult(Exercise exercise);

    Observable<ExerciseType> doGetExerciseTypes();

    IRxWebSocket doStartStreaming(Exercise exercise);
    void doSendMeasurement(IRxWebSocket stream, Measurement measurement);

    Single<ExerciseGoal> doGetExerciseGoal(Exercise exercise);
}
