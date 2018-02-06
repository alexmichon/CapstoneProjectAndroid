package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder.exercise_type.list.ExerciseTypesInteractor;

import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 24/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ExerciseTypesInteractorTest {

    private ExerciseTypesInteractor mInteractor;

    @Mock
    private IExerciseTypeManager mExerciseTypeManager;

    @Before
    public void setup() {
        mInteractor = new ExerciseTypesInteractor(mExerciseTypeManager);
    }

    @Test
    public void doLoadExerciseTypesShouldCallRepository() {
        // when
        mInteractor.doLoadExerciseTypes();

        // then
        verify(mExerciseTypeManager).doGetExerciseTypes();
    }
}
