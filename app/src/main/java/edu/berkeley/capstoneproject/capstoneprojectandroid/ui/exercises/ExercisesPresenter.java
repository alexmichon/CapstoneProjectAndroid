package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercises;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExercisesPresenter extends BasePresenter<ExercisesContract.View> implements ExercisesContract.Presenter {


    @Inject
    public ExercisesPresenter(ExercisesContract.View view) {
        super(view);
    }


    @Override
    public void loadExercises() {

    }

    @Override
    public void onExerciseClick(Exercise exercise) {

    }
}
