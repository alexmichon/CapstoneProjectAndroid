package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise;

import java.util.Arrays;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.ISpecification;
import io.reactivex.Observable;

/**
 * Created by Alex on 09/11/2017.
 */

public class ExerciseTypeRepository implements IExerciseTypeRepository {

    public static final int ID_TEST_EXERCISE = 1;

    private static ExerciseType EXERCISE_TYPES[] = {
            new ExerciseType(ID_TEST_EXERCISE, "Test Exercise", "This is a test exercise")
    };

    @Inject
    public ExerciseTypeRepository() {

    }

    @Override
    public void create(ExerciseType item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void create(Iterable<ExerciseType> items) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(ExerciseType item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(ExerciseType item) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(ISpecification specification) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<ExerciseType> query() {
        return Observable.fromIterable(Arrays.asList(EXERCISE_TYPES));
    }

    @Override
    public Observable<ExerciseType> query(ISpecification specification) {
        throw new UnsupportedOperationException();
    }
}
