package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ITrainingManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.IMeasurementManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;
import io.reactivex.Flowable;

/**
 * Created by Alex on 10/11/2017.
 */

public class ExerciseInteractor extends BaseInteractor implements ExerciseContract.Interactor {

    private final ITrainingManager mExerciseManager;
    private final IMeasurementManager mMeasurementManager;

    @Inject
    public ExerciseInteractor(ITrainingManager exerciseManager, IMeasurementManager measurementManager) {
        mExerciseManager = exerciseManager;
        mMeasurementManager = measurementManager;
    }

    /*@Override
    public Single<ExerciseGoal> doCreateExerciseGoal() {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseGoalCreator()
                .flatMap(new Function<ExerciseGoalCreator, SingleSource<? extends ExerciseGoal>>() {
                    @Override
                    public SingleSource<? extends ExerciseGoal> apply(@NonNull final ExerciseGoalCreator exerciseGoalCreator) throws Exception {
                        return getDataManager().getSessionHelper().getTrainingService().getExercise()
                                .flatMap(new Function<Exercise, SingleSource<? extends ExerciseGoal>>() {
                                    @Override
                                    public SingleSource<? extends ExerciseGoal> apply(@NonNull Exercise exercise) throws Exception {
                                        return getDataManager().getApiHelper().getExerciseService().doCreateExerciseGoal(exercise, exerciseGoalCreator);
                                    }
                                });
                    }
                });
    }*/


    /*@Override
    public Single<Exercise> doCreateExercise() {
        return getDataManager().getSessionHelper().getExerciseCreatorService().getExerciseCreator()
                .flatMap(new Function<ExerciseCreator, SingleSource<? extends Exercise>>() {
                    @Override
                    public SingleSource<? extends Exercise> apply(@NonNull ExerciseCreator exerciseCreator) throws Exception {
                        return getDataManager().getApiHelper().getExerciseService().doCreateExercise(exerciseCreator);
                    }
                })
                .flatMap(new Function<Exercise, SingleSource<? extends Exercise>>() {
                    @Override
                    public SingleSource<? extends Exercise> apply(@NonNull Exercise exercise) throws Exception {
                        return getDataManager().getSessionHelper().getTrainingService().setExercise(exercise).toSingleDefault(exercise);
                    }
                });
    }*/

    @Override
    public Exercise getExercise() {
        return mExerciseManager.getCurrentExercise();
    }

    @Override
    public Completable doStartExercise() {
        return mExerciseManager.doStartSensors();
    }

    @Override
    public Completable doStartStreaming() {
        return mExerciseManager.doStartStreaming();
    }

    @Override
    public Completable doStopExercise() {
        return Completable.concatArray(
                mExerciseManager.doStopStreaming().andThen(mExerciseManager.doStopExercise()),
                mExerciseManager.doStopSensors());
    }


    @Override
    public Flowable<Measurement> doListenMeasurements() {
        return mExerciseManager.listen();
    }

    @Override
    public void doSaveMeasurement(final Measurement measurement) {
        mExerciseManager.doSendMeasurement(measurement);
    }
}
