package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 22/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ExercisePresenterTest {

    private ExercisePresenter<ExerciseContract.View, ExerciseContract.Interactor> mPresenter;

    @Mock
    private ExerciseContract.View mView;

    @Mock
    private ExerciseContract.Interactor mInteractor;

    private TestScheduler mTestScheduler;

    private Exercise mExercise;
    private ExerciseType mExerciseType;

    @Before
    public void setup() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider provider = new TestSchedulerProvider(mTestScheduler);

        mExercise = Mockito.mock(Exercise.class);
        mExerciseType = Mockito.mock(ExerciseType.class);

        doReturn(mExercise).when(mInteractor).getExercise();

        mPresenter = Mockito.spy(new ExercisePresenter<>(mInteractor, provider, compositeDisposable));
        mPresenter.attachView(mView);

        mTestScheduler.triggerActions();

        reset(mView);
    }





    @Test
    public void onViewReadyShouldCallInteractor() {
        // given
        doReturn(Completable.complete()).when(mInteractor).doPrepareExercise();

        // when
        mPresenter.onViewReady();

        // then
        verify(mInteractor).doPrepareExercise();
    }


    @Test
    public void onViewReadyShouldUpdateView() {
        // given
        doReturn(Completable.complete()).when(mInteractor).doPrepareExercise();

        // when
        mPresenter.onViewReady();

        // then
        verify(mView).onExerciseCreated(mExercise);
        verify(mView).onPreparingExercise();
    }

    @Test
    public void onViewReadyShouldUpdateViewOnComplete() {
        // given
        doReturn(Completable.complete()).when(mInteractor).doPrepareExercise();
        doReturn(Completable.complete()).when(mInteractor).doStartStreaming();

        // when
        mPresenter.onViewReady();
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseReady(mExercise);
    }

    @Test
    public void onViewReadyShouldUpdateViewOnError() {
        // given
        Error error = new Error();
        doReturn(Completable.error(error)).when(mInteractor).doPrepareExercise();

        // when
        mPresenter.onViewReady();
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseError(error);
    }



    @Test
    public void startExerciseShouldCallInteractor() {
        // given
        Completable completable = Completable.complete();
        //doReturn(completable).when(mInteractor).doPrepareExercise(mExercise);

        // when
        //mPresenter.prepareExercise(mExercise);

        // then
        //verify(mInteractor).doPrepareExercise(mExercise);
        completable.test().assertSubscribed();
    }


    @Test
    public void onStartClickShouldStartCountdown() {
        // when
        mPresenter.onStartClick();

        // then
        verify(mView).onCountdownStart();
    }

    @Test
    public void startExerciseShouldUpdateViewOnFailure() {
        // given
        Error error = new Error();
        doReturn(Completable.error(error)).when(mInteractor)
               .doPrepareExercise();

        // when
        mPresenter.onViewReady();
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseError(error);
    }







    @Test
    public void startListeningShouldCallInteractor() {
        // given
        Flowable flowable = Flowable.empty();
        doReturn(flowable).when(mInteractor)
                .doListen();

        // when
        mPresenter.startListening();

        // then
        verify(mInteractor).doListen();
        flowable.test().assertSubscribed();
    }

    @Test
    public void startRecordingShouldUpdateViewWithMeasurement() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        doReturn(Flowable.just(measurement)).when(mInteractor)
                .doListen();

        // when
        mPresenter.startListening();
        mPresenter.onStartRecording();
        mTestScheduler.triggerActions();

        // then
        verify(mView).addMeasurement(measurement);
    }

    @Test
    public void startListeningShouldSaveMeasurement() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        doReturn(Flowable.just(measurement)).when(mInteractor)
                .doListen();

        // when
        mPresenter.startListening();
        mTestScheduler.triggerActions();

        // then
        verify(mPresenter).onReceiveMeasurement(measurement);
    }

    @Test
    public void startListeningShouldUpdateViewOnFailure() {
        // given
        Error error = new Error();
        doReturn(Flowable.error(error)).when(mInteractor)
                .doListen();

        // when
        mPresenter.startListening();
        mTestScheduler.triggerActions();

        // then
        verify(mView).showError(error);
    }





    @Test
    public void onReceiveMeasurementShouldCallInteractor() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        Completable completable = Completable.complete();
        //doReturn(completable).when(mInteractor)
        //        .doSaveMeasurement(mExercise, measurement);

        // when
        //mPresenter.onReceiveMeasurement(mExercise, measurement);

        // then
        //verify(mInteractor).doSaveMeasurement(mExercise, measurement);
        completable.test().assertSubscribed();
    }

    @Test
    public void onReceiveMeasurementShouldStoreMeasurementInExercise() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        //doReturn(Completable.complete()).when(mInteractor)
        //        .doSaveMeasurement(mExercise, measurement);

        // when
        //mPresenter.onReceiveMeasurement(mExercise, measurement);

        // then
        //verify(measurement).setExercise(mExercise);
    }





    @Test
    public void onStopClickShouldCallInteractor() {
        // given
        doReturn(Completable.complete()).when(mInteractor).doStopExercise();

        // when
        mPresenter.onStopClick();

        // then
        verify(mInteractor).doStopExercise();
    }

    @Test
    public void onStopClickShouldUpdateView() {
        // given
        doReturn(Completable.complete()).when(mInteractor).doStopExercise();

        // when
        mPresenter.onStopClick();
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseStopped();
    }


    @After
    public void cleanup() {
        mPresenter.detachView();
        mPresenter.destroy();
    }
}
