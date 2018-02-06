package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.builder;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseTypeFactory;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 22/01/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class ExerciseBuilderPresenterTest {

    private ExerciseBuilderPresenter<ExerciseBuilderContract.View, ExerciseBuilderContract.Interactor> mPresenter;

    @Mock
    private ExerciseBuilderContract.View mView;

    @Mock
    private ExerciseBuilderContract.Interactor mInteractor;

    private TestScheduler mTestScheduler;

    @Before
    public void setUp() throws Exception {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        mPresenter = new ExerciseBuilderPresenter<>(mInteractor, testSchedulerProvider, compositeDisposable);

        mPresenter.attachView(mView);
    }

    @After
    public void tearDown() throws Exception {
        mPresenter.detachView();
        mPresenter.destroy();
    }

    @Test
    public void onExerciseTypeSelectShouldUpdateView() throws Exception {
        // given
        ExerciseType exerciseType = ExerciseTypeFactory.create();
        doReturn(Single.never()).when(mInteractor).doCreateExercise(exerciseType);

        // when
        mPresenter.onExerciseTypeSelect(exerciseType);

        // then
        verify(mView).showLoading();
    }

    @Test
    public void onExerciseTypeSelectShouldCallInteractor() {
        // given
        ExerciseType exerciseType = ExerciseTypeFactory.create();
        doReturn(Single.never()).when(mInteractor).doCreateExercise(exerciseType);

        // when
        mPresenter.onExerciseTypeSelect(exerciseType);

        // then
        verify(mInteractor).doCreateExercise(exerciseType);
    }

    @Test
    public void onExerciseTypeSelectShouldUpdateViewOnSuccess() {
        // given
        ExerciseType exerciseType = ExerciseTypeFactory.create();
        Exercise exercise = ExerciseFactory.builder()
                .withExerciseType(exerciseType)
                .build();

        doReturn(Single.just(exercise)).when(mInteractor).doCreateExercise(exerciseType);

        // when
        mPresenter.onExerciseTypeSelect(exerciseType);
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseBuilt();
    }


    @Test
    public void onExerciseTypeSelectShouldUpdateViewOnFailure() {
        // given
        ExerciseType exerciseType = ExerciseTypeFactory.create();
        Error error = new Error();

        doReturn(Single.error(error)).when(mInteractor).doCreateExercise(exerciseType);

        // when
        mPresenter.onExerciseTypeSelect(exerciseType);
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseError(error);
    }

}