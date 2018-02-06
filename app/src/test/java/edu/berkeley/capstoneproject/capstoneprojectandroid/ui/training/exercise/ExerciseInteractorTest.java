package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.IBluetoothHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.MeasurementFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ITrainingManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.IMeasurementManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IExerciseService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth.IMeasurementService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import io.reactivex.Completable;
import io.reactivex.Observable;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 23/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ExerciseInteractorTest {

    private ExerciseInteractor mInteractor;

    @Mock
    private ITrainingManager mTrainingManager;

    @Before
    public void setup() {
        mInteractor = new ExerciseInteractor(mTrainingManager);
    }

    @Test
    public void getExerciseShouldCallTrainingManager() {
        // given

        // when
        mInteractor.getExercise();

        // then
        verify(mTrainingManager).getCurrentExercise();
    }

    @Test
    public void doPrepareExerciseShouldCallTrainingManager() {
        // given

        // when
        mInteractor.doPrepareExercise();

        // then
        verify(mTrainingManager).doStartSensors();
    }




    @Test
    public void doStartStreamingShouldCallTrainingManager() {
        // given

        // when
        mInteractor.doStartStreaming();

        // then
        verify(mTrainingManager).doStartStreaming();
    }

    @Test
    public void doStopExerciseShouldCallTrainingManager() {
        // given
        doReturn(Completable.complete()).when(mTrainingManager).doStopStreaming();
        doReturn(Completable.complete()).when(mTrainingManager).doStopExercise();
        doReturn(Completable.complete()).when(mTrainingManager).doStopSensors();

        // when
        mInteractor.doStopExercise();

        // then
        verify(mTrainingManager).doStopExercise();
        verify(mTrainingManager).doStopStreaming();
        verify(mTrainingManager).doStopSensors();
    }

    @Test
    public void doListenShouldCallTrainingManager() {
        // given

        // when
        mInteractor.doListen();

        // then
        verify(mTrainingManager).doListen();
    }

    @Test
    public void doSaveMeasurementShouldCallTrainingManager() {
        // given
        Measurement measurement = MeasurementFactory.builder().build();

        // when
        mInteractor.doSaveMeasurement(measurement);

        // then
        verify(mTrainingManager).doSendMeasurement(measurement);
    }
}
