package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.net.ConnectivityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 22/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class SplashPresenterTest {

    private SplashPresenter<SplashContract.View, SplashContract.Interactor> mPresenter;

    @Mock
    private SplashContract.View mView;

    @Mock
    private SplashContract.Interactor mInteractor;

    private TestScheduler mTestScheduler;

    @Mock
    ConnectivityManager mConnectivityManager;

    @Before
    public void setup() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();

        mTestScheduler = new TestScheduler();
        TestSchedulerProvider provider = new TestSchedulerProvider(mTestScheduler);

        mPresenter = Mockito.spy(new SplashPresenter<>(mInteractor, provider, compositeDisposable, mConnectivityManager));
        mPresenter.attachView(mView);
    }

    @Test
    public void onStartShouldCheckNetwork() {
        // given
        doReturn(Single.never()).when(mInteractor).doRestoreAuthentication();
        doReturn(Single.never()).when(mInteractor).doCheckNetworkState();

        // when
        mPresenter.onStart();

        // then
        verify(mInteractor).doCheckNetworkState();
    }

    @Test
    public void onStartShouldRestoreAuthentication() {
        // given
        doReturn(Single.never()).when(mInteractor).doRestoreAuthentication();
        doReturn(Single.never()).when(mInteractor).doCheckNetworkState();

        // when
        mPresenter.onStart();

        // then
        verify(mInteractor).doRestoreAuthentication();
    }

    @After
    public void cleanup() {
        mPresenter.detachView();
        mPresenter.destroy();
    }
}
