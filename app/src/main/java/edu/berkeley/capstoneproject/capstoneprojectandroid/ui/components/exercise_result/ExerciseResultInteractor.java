package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.components.exercise_result;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultInteractor extends BaseInteractor implements ExerciseResultContract.Interactor {

    @Inject
    public ExerciseResultInteractor() {
    }
}
