package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.IExerciseTypeRepository;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.list.ExerciseTypesInteractor;

import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 24/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ExerciseTypesInteractorTest {

    private ExerciseTypesInteractor mInteractor;

    @Mock
    private IDataManager mDataManager;

    @Mock
    private IExerciseTypeRepository mRepository;

    @Before
    public void setup() {
        mInteractor = new ExerciseTypesInteractor(mDataManager, mRepository);
    }

    @Test
    public void doLoadExerciseTypesShouldCallRepository() {
        // when
        mInteractor.doLoadExerciseTypes();

        // then
        verify(mRepository).query();
    }
}
