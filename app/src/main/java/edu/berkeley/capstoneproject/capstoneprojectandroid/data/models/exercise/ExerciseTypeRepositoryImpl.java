package edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.exercise;

import java.util.Arrays;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.Specification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;

/**
 * Created by Alex on 09/11/2017.
 */

public class ExerciseTypeRepositoryImpl implements ExerciseTypeRepository {

    private static ExerciseType EXERCISE_TYPES[] = {
            new ExerciseType("Test Exercise", "This is a test exercise")
    };


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
    public void delete(Specification specification) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Observable<ExerciseType> query() {
        return Observable.fromIterable(Arrays.asList(EXERCISE_TYPES));
    }

    @Override
    public Observable<ExerciseType> query(Specification specification) {
        throw new UnsupportedOperationException();
    }
}
