package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 07/12/2017.
 */

public class ExerciseTypeFactory {

    public static int ID = 0;

    public static ExerciseType create() {
        ID++;
        return new ExerciseType(ID, "ExerciseType" + ID, "This is the exercise type n°" + ID);
    }
}
