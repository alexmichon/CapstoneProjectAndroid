package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.support.design.widget.NavigationView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuItem;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 21/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    private MainPresenter<MainContract.View, MainContract.Interactor> mPresenter;

    @Mock
    private MainContract.Interactor mInteractor;

    @Mock
    private MainContract.View mView;

    private TestScheduler mTestScheduler;

    @Before
    public void setup() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);

        mPresenter = Mockito.spy(new MainPresenter<>(mInteractor, testSchedulerProvider, compositeDisposable));
        mPresenter.attachView(mView);
    }

    @Test
    public void homeMainMenuClickShouldShowHomeFragment() {
        // given
        doNothing().when(mView).showHomeFragment();

        // when
        mPresenter.onMainMenuItemClick(new MainMenuItem(MainMenuItem.HOME_TITLE, MainMenuItem.HOME_ICON));

        // then
        verify(mView).showHomeFragment();
    }

    @After
    public void cleanup() {
        mPresenter.detachView();
        mPresenter.destroy();
    }
}
