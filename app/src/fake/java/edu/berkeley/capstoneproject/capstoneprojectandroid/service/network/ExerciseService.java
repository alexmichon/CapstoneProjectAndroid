package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import android.content.Context;
import android.net.Uri;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.ExerciseTypeFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoalFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResultFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Alex on 28/11/2017.
 */

public class ExerciseService implements IExerciseService {

    private final Context mContext;

    @Inject
    public ExerciseService(Context context) {
        mContext = context;
    }

    @Override
    public Single<Exercise> doCreateExercise(ExerciseType exerciseType) {
        return Single.just(new Exercise(0, exerciseType));
    }

    @Override
    public Single<Measurement> doSaveMeasurement(Measurement measurement) {
        measurement.setId(0);
        return Single.just(measurement);
    }

    @Override
    public Single<Measurement> getMaxMeasurement() {
        return Single.never();
    }

    @Override
    public Single<ExerciseGoal> doUpdateExerciseGoal(ExerciseGoal exerciseGoal) {
        return Single.just(exerciseGoal);
    }

    @Override
    public Single<ExerciseGoal> doGetExerciseGoal(ExerciseType exerciseType) {
        return Single.just(ExerciseGoalFactory.builder()
                .build()
        );
    }

    @Override
    public Single<ExerciseResult> doGetExerciseResult(Exercise exercise) {
        return Single.just(ExerciseResultFactory.builder()
                .build()
        );
    }

    @Override
    public Observable<ExerciseType> doGetExerciseTypes() {
        return Observable.just(
                ExerciseTypeFactory.builder()
                        .withName("Test Exercise Type 1")
                        .withDescription("This is a test exercise")
                        .build(),
                ExerciseTypeFactory.builder()
                        .withName("Test Exercise Type 2")
                        .withDescription("This is a" + new String(new char[500]).replace("\0", " very ") + "long description")
                        .build(),
                ExerciseTypeFactory.builder()
                        .withName("Vulcan salute")
                        .withDescription("Live long and prosper !")
                        .withVideo(Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.vulcan))
                        .build(),
                ExerciseTypeFactory.builder()
                        .withName("Shoulder abduction")
                        .withDescription("This is how you do it !")
                        .withVideo(Uri.parse("android.resource://" + mContext.getPackageName() + "/" + R.raw.shoulder))
                        .build(),
                ExerciseTypeFactory.builder()
                        .withName("Youtube test")
                        .withYoutubeVideo("HHjKzr6tLz0")
                        .build()
        );
    }
}
