package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
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

        doReturn(Single.just(mExercise)).when(mInteractor).doCreateExercise(mExerciseType);
        doReturn(mExerciseType).when(mView).getExerciseType();

        mPresenter = Mockito.spy(new ExercisePresenter<>(mInteractor, provider, compositeDisposable));
        mPresenter.attachView(mView);

        mTestScheduler.triggerActions();

        reset(mView);
    }





    @Test
    public void onStartClickShouldCallInteractor() {
        // given
        Single single = Single.just(mExercise);
        doReturn(single).when(mInteractor).doCreateExercise(mExerciseType);

        // when
        mPresenter.onStartClick();

        // then
        verify(mInteractor).doCreateExercise(mExerciseType);
        single.test().assertSubscribed();
    }


    @Test
    public void onStartClickShouldUpdateView() {
        // given
        doReturn(Single.just(mExercise)).when(mInteractor).doCreateExercise(mExerciseType);

        // when
        mPresenter.onStartClick();

        // then
        verify(mView).onCreatingExercise();
    }

    @Test
    public void onStartClickShouldStartExerciseOnComplete() {
        // given
        doReturn(Single.just(mExercise)).when(mInteractor).doCreateExercise(mExerciseType);
        doNothing().when(mPresenter).startExercise(any(Exercise.class));

        // when
        mPresenter.onStartClick();
        mTestScheduler.triggerActions();

        // then
        verify(mPresenter).startExercise(mExercise);
    }

    @Test
    public void onStartClickShouldUpdateViewOnComplete() {
        // given
        doReturn(Single.just(mExercise)).when(mInteractor).doCreateExercise(mExerciseType);
        doNothing().when(mPresenter).startExercise(any(Exercise.class));

        // when
        mPresenter.onStartClick();
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseCreated(mExercise);
    }

    @Test
    public void onStartClickShouldUpdateViewOnError() {
        // given
        Error error = new Error();
        doReturn(Single.error(error)).when(mInteractor).doCreateExercise(mExerciseType);

        // when
        mPresenter.onStartClick();
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseError(error);
    }



    @Test
    public void startExerciseShouldCallInteractor() {
        // given
        Completable completable = Completable.complete();
        doReturn(completable).when(mInteractor).doStartExercise(mExercise);

        // when
        mPresenter.startExercise(mExercise);

        // then
        verify(mInteractor).doStartExercise(mExercise);
        completable.test().assertSubscribed();
    }


    @Test
    public void startExerciseShouldUpdateView() {
        // given
        doReturn(Completable.complete()).when(mInteractor)
                .doStartExercise(mExercise);

        // when
        mPresenter.startExercise(mExercise);

        // then
        verify(mView).onStartingExercise();
    }

    @Test
    public void startExerciseShouldUpdateViewOnSuccess() {
        // given
        doReturn(Completable.complete()).when(mInteractor)
                .doStartExercise(mExercise);

        doReturn(Flowable.empty()).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.startExercise(mExercise);
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseStarted(mExercise);
    }

    @Test
    public void startExerciseShouldStartListeningOnSuccess() {
        // given
        doReturn(Completable.complete()).when(mInteractor)
                .doStartExercise(mExercise);

        doReturn(Flowable.empty()).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.onStartClick();
        mTestScheduler.triggerActions();

        // then
        verify(mPresenter).startListening(mExercise);
    }

    @Test
    public void startExerciseShouldUpdateViewOnFailure() {
        // given
        Error error = new Error();
        doReturn(Completable.error(error)).when(mInteractor)
                .doStartExercise(any(Exercise.class));

        // when
        mPresenter.onStartClick();
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseError(error);
    }







    @Test
    public void startListeningShouldCallInteractor() {
        // given
        Flowable flowable = Flowable.empty();
        doReturn(flowable).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.startListening(mExercise);

        // then
        verify(mInteractor).doListenMeasurements();
        flowable.test().assertSubscribed();
    }

    @Test
    public void startListeningShouldUpdateViewWithMeasurement() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        doReturn(Flowable.just(measurement)).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.startListening(mExercise);
        mTestScheduler.triggerActions();

        // then
        verify(mView).addMeasurement(measurement);
    }

    @Test
    public void startListeningShouldSaveMeasurement() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        doReturn(Flowable.just(measurement)).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.startListening(mExercise);
        mTestScheduler.triggerActions();

        // then
        verify(mPresenter).onReceiveMeasurement(mExercise, measurement);
    }

    @Test
    public void startListeningShouldUpdateViewOnFailure() {
        // given
        Error error = new Error();
        doReturn(Flowable.error(error)).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.startListening(mExercise);
        mTestScheduler.triggerActions();

        // then
        verify(mView).showError(error);
    }





    @Test
    public void onReceiveMeasurementShouldCallInteractor() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        Completable completable = Completable.complete();
        doReturn(completable).when(mInteractor)
                .doSaveMeasurement(mExercise, measurement);

        // when
        mPresenter.onReceiveMeasurement(mExercise, measurement);

        // then
        verify(mInteractor).doSaveMeasurement(mExercise, measurement);
        completable.test().assertSubscribed();
    }

    @Test
    public void onReceiveMeasurementShouldStoreMeasurementInExercise() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        doReturn(Completable.complete()).when(mInteractor)
                .doSaveMeasurement(mExercise, measurement);

        // when
        mPresenter.onReceiveMeasurement(mExercise, measurement);

        // then
        verify(measurement).setExercise(mExercise);
    }





    @Test
    public void onStopClickShouldCallInteractor() {
        // when
        mPresenter.onStopClick();

        // then
        verify(mInteractor).doStopExercise();
    }

    @Test
    public void onStopClickShouldUpdateView() {
        // when
        mPresenter.onStopClick();

        // then
        verify(mView).onExerciseStopped(any(Exercise.class));
    }


    @After
    public void cleanup() {
        mPresenter.detachView();
        mPresenter.destroy();
    }
}
