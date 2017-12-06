package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 22/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class ExerciseTypePresenterTest {

    private ExerciseTypePresenter<ExerciseTypeContract.View, ExerciseTypeContract.Interactor> mPresenter;

    @Mock
    private ExerciseTypeContract.View mView;

    @Mock
    private ExerciseTypeContract.Interactor mInteractor;

    private TestScheduler mTestScheduler;

    @Before
    public void setup() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider provider = new TestSchedulerProvider(mTestScheduler);

        mPresenter = Mockito.spy(new ExerciseTypePresenter<>(mInteractor, provider, compositeDisposable));
        mPresenter.onAttach(mView);
    }

    @Test
    public void onLoadExerciseTypesShouldShowLoading() {
        // given
        doReturn(Observable.empty()).when(mInteractor)
                .doLoadExerciseTypes();

        // when
        mPresenter.onLoadExerciseTypes();

        // then
        verify(mView).onExerciseTypesLoading();
    }

    @Test
    public void onLoadExerciseTypesShouldNotifyViewWhenNewExerciseTypes() {
        // given
        ExerciseType exerciseType = Mockito.mock(ExerciseType.class);

        doReturn(Observable.just(exerciseType)).when(mInteractor)
                .doLoadExerciseTypes();

        // when
        mPresenter.onLoadExerciseTypes();
        mTestScheduler.triggerActions();

        // then
        verify(mView).addExerciseType(exerciseType);
    }

    @Test
    public void onLoadExerciseTypesShouldNotifyViewWhenDoneLoading() {
        // given
        ExerciseType exerciseType = Mockito.mock(ExerciseType.class);

        doReturn(Observable.just(exerciseType)).when(mInteractor)
                .doLoadExerciseTypes();

        // when
        mPresenter.onLoadExerciseTypes();
        mTestScheduler.triggerActions();

        // then
        verify(mView).onExerciseTypesDoneLoading();
    }

    @After
    public void cleanup() {
        mPresenter.onDetach();
    }
}