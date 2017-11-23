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
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
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

        doReturn(Single.just(mExercise)).when(mInteractor).doCreateExercise(mExerciseType);

        mPresenter = Mockito.spy(new ExercisePresenter<>(mInteractor, provider, compositeDisposable));
        mPresenter.onAttach(mView, mExerciseType);

        mTestScheduler.triggerActions();

        reset(mView);
    }




    @Test
    public void onCreateExerciseShouldUpdateView() {
        // given
        doReturn(Single.just(mExercise)).when(mInteractor).doCreateExercise(mExerciseType);

        // when
        mPresenter.createExercise();

        // then
        verify(mView).showLoading(anyString(), eq(false));
    }

    @Test
    public void onCreateExerciseShouldUpdateViewOnComplete() {
        // given
        doReturn(Single.just(mExercise)).when(mInteractor).doCreateExercise(mExerciseType);

        // when
        mPresenter.createExercise();
        mTestScheduler.triggerActions();

        // then
        verify(mView).hideLoading();
    }

    @Test
    public void onCreateExerciseShouldUpdateViewOnError() {
        // given
        Error error = new Error();
        doReturn(Single.error(error)).when(mInteractor).doCreateExercise(mExerciseType);

        // when
        mPresenter.createExercise();
        mTestScheduler.triggerActions();

        // then
        verify(mView).showError(error);
    }




    @Test
    public void onStartClickShouldShowLoading() {
        // given
        doReturn(Completable.complete()).when(mInteractor)
                .doStartExercise(any(Exercise.class));

        // when
        mPresenter.onStartClick();

        // then
        verify(mView).onWaitToStart();
    }

    @Test
    public void onStartClickShouldUpdateViewOnSuccess() {
        // given
        doReturn(Completable.complete()).when(mInteractor)
                .doStartExercise(any(Exercise.class));

        doReturn(Flowable.empty()).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.onStartClick();
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseStart();
    }

    @Test
    public void onStartClickShouldStartListeningOnSuccess() {
        // given
        doReturn(Completable.complete()).when(mInteractor)
                .doStartExercise(any(Exercise.class));

        doReturn(Flowable.empty()).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.onStartClick();
        mTestScheduler.triggerActions();

        // then
        verify(mPresenter).startListening();
    }

    @Test
    public void onStartClickShouldUpdateViewOnFailure() {
        // given
        Error error = new Error();
        doReturn(Completable.error(error)).when(mInteractor)
                .doStartExercise(any(Exercise.class));

        // when
        mPresenter.onStartClick();
        mTestScheduler.triggerActions();

        // then
        verify(mView).showError(error);
    }

    @Test
    public void onStartListeningShouldCallInteractor() {
        // given
        doReturn(Flowable.empty()).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.startListening();

        // then
        verify(mInteractor).doListenMeasurements();
    }

    @Test
    public void onStartListeningShouldUpdateViewWithMeasurement() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        doReturn(Flowable.just(measurement)).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.startListening();
        mTestScheduler.triggerActions();

        // then
        verify(mView).addMeasurement(measurement);
    }

    @Test
    public void onStartListeningShouldSaveMeasurement() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        doReturn(Flowable.just(measurement)).when(mInteractor)
                .doListenMeasurements();

        // when
        mPresenter.startListening();
        mTestScheduler.triggerActions();

        // then
        verify(mPresenter).onReceiveMeasurement(measurement);
    }

    @Test
    public void onStartListeningShouldUpdateViewOnFailure() {
        // given
        Error error = new Error();
        doReturn(Flowable.error(error)).when(mInteractor)
                .doListenMeasurements();

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
        doReturn(Completable.complete()).when(mInteractor)
                .doSaveMeasurement(mExercise, measurement);

        // when
        mPresenter.onReceiveMeasurement(measurement);

        // then
        verify(mInteractor).doSaveMeasurement(mExercise, measurement);
    }

    @Test
    public void onReceiveMeasurementShouldStoreMeasurementInExercise() {
        // given
        Measurement measurement = Mockito.mock(Measurement.class);
        doReturn(Completable.complete()).when(mInteractor)
                .doSaveMeasurement(mExercise, measurement);

        // when
        mPresenter.onReceiveMeasurement(measurement);

        // then
        verify(mExercise).addMeasurement(measurement);
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
        verify(mView).onExerciseStop();
    }


    @After
    public void cleanup() {
        mPresenter.onDetach();
    }
}
