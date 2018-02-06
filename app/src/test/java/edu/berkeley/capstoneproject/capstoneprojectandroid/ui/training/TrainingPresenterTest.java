package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 22/01/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class TrainingPresenterTest {

    private TrainingPresenter<TrainingContract.View, TrainingContract.Interactor> mTrainingPresenter;

    @Mock
    private TrainingContract.View mView;

    @Mock
    private TrainingContract.Interactor mInteractor;

    private TestScheduler mTestScheduler;

    @Before
    public void before() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mTrainingPresenter = new TrainingPresenter<>(mInteractor, testSchedulerProvider, compositeDisposable);

        mTrainingPresenter.attachView(mView);
    }

    @Test
    public void onBluetoothDeviceSelectShouldShowExerciseBuilder() {
        // when
        mTrainingPresenter.onBluetoothDeviceSelect();

        // then
        verify(mView).showExerciseBuilderFragment();
    }

    @Test
    public void onExerciseSummaryStartShouldShowExercise() {
        // when
        mTrainingPresenter.onExerciseSummaryStart();

        // then
        verify(mView).showExerciseFragment();
    }

    @Test
    public void onExerciseSummaryBackShouldShowExerciseBuilder() {
        // when
        mTrainingPresenter.onExerciseSummaryBack();

        // then
        mView.showExerciseBuilderFragment();
    }

    @Test
    public void onExerciseDoneShouldShowExerciseResult() {
        // when
        mTrainingPresenter.onExerciseDone();

        // then
        mView.showExerciseResultFragment();
    }

    @Test
    public void onExerciseBuiltShouldShowSummary() {
        // when
        mTrainingPresenter.onExerciseBuilt();

        // then
        mView.showExerciseSummaryFragment();
    }

    @Test
    public void onExerciseResultMenuShouldMoveToMainActivity() {
        // when
        mTrainingPresenter.onExerciseResultMenu();

        // then
        mView.moveToMainActivity();
    }

    @Test
    public void onExerciseResultRetryShouldShowExercise() {
        // when
        mTrainingPresenter.onExerciseResultRetry();

        // then
        mView.showExerciseFragment();
    }

    @After
    public void after() {
        mTrainingPresenter.detachView();
        mTrainingPresenter.destroy();
    }
}
