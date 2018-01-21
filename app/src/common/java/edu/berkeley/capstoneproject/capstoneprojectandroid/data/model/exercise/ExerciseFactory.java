package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

/**
 * Created by Alex on 20/12/2017.
 */

public class ExerciseFactory {

    private static int ID = 0;

    public static Exercise fromBuilder(Exercise.Builder builder) {
        return new Exercise(ID++, builder.getExerciseTypeId(), builder.getName());
    }
}
