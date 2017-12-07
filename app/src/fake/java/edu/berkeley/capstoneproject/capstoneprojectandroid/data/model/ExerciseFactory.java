package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 07/12/2017.
 */

public class ExerciseFactory {

    private static int ID = 0;

    public static Builder builder() {
        return new Builder();
    }

    public static ListBuilder listBuilder() {
        return new ListBuilder();
    }


    public static class Builder {

        private ExerciseType mExerciseType;
        private List<Measurement> mMeasurementList = new ArrayList<>();

        public Builder() {
            mExerciseType = ExerciseTypeFactory.create();
        }

        public Builder withExerciseType(ExerciseType exerciseType) {
            mExerciseType = exerciseType;
            return this;
        }

        public Builder withMeasurements(int count) {
            for (int i = 0; i < count; i++) {
                mMeasurementList.add(MeasurementFactory.builder().build());
            }
            return this;
        }

        public Builder withMeasurements(List<Measurement> measurements) {
            mMeasurementList = measurements;
            return this;
        }

        public Exercise build() {
            Exercise exercise = new Exercise(ID++, mExerciseType);
            for (Measurement m: mMeasurementList) {
                exercise.addMeasurement(m);
            }

            return exercise;
        }

    }

    public static class ListBuilder {
        private List<Builder> mExercises;

        public ListBuilder() {
            mExercises = new ArrayList<>();
        }

        public ListBuilder add(int count) {
            mExercises = new ArrayList<>(count);
            for (int i = 0; i < count; i++) {
                addExercise(builder());
            }
            return this;
        }

        public ListBuilder addExercise(Builder exercise) {
            mExercises.add(exercise);
            return this;
        }

        public List<Exercise> build() {
            List<Exercise> exercises = new ArrayList<>();
            for (Builder b: mExercises) {
                exercises.add(b.build());
            }
            return exercises;
        }
    }
}
