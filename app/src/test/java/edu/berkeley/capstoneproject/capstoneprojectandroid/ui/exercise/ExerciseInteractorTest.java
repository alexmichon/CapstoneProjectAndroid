package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.ExerciseResponse;
import io.reactivex.Single;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 23/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ExerciseInteractorTest {

    private ExerciseInteractor mInteractor;

    @Mock
    private Exercise mExercise;

    @Mock
    private ExerciseType mExerciseType;

    @Mock
    private DataManager mDataManager;

    @Mock
    private IApiHelper mApiHelper;

    @Mock
    private IBluetoothHelper mBluetoothHelper;

    @Mock
    private edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IExerciseService mApiExerciseService;

    @Mock
    private edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.service.IExerciseService mBluetoothExerciseService;

    @Before
    public void setup() {
        doReturn(mApiHelper).when(mDataManager).getApiHelper();
        doReturn(mApiExerciseService).when(mApiHelper).getExerciseService();

        doReturn(mBluetoothHelper).when(mDataManager).getBluetoothHelper();
        doReturn(mBluetoothExerciseService).when(mBluetoothHelper).getExerciseService();

        mExercise = Mockito.mock(Exercise.class);
        mExerciseType = Mockito.mock(ExerciseType.class);

        mInteractor = new ExerciseInteractor(mDataManager);
    }

    @Test
    public void doCreateExerciseShouldCallApi() {
        // given
        ExerciseRequest request = new ExerciseRequest(mExerciseType);
        doReturn(Single.never()).when(mApiExerciseService).doCreateExercise(request);

        // when
        mInteractor.doCreateExercise(mExerciseType);

        // then
        verify(mApiExerciseService).doCreateExercise(request);
    }

    @Test
    public void doCreateExerciseShouldReturnCorrectExercise() {
        // given
        ExerciseResponse response = Mockito.mock(ExerciseResponse.class);
        doReturn(mExercise).when(response).getExercise(mExerciseType);
        doReturn(Single.just(response)).when(mApiExerciseService).doCreateExercise(any(ExerciseRequest.class));

        // when
        Exercise exercise = mInteractor.doCreateExercise(mExerciseType).blockingGet();

        // then
        assertEquals(exercise, mExercise);
    }
}
