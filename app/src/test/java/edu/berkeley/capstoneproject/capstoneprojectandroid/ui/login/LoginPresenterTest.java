package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static junit.framework.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 09/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private LoginContract.View mView;

    @Mock
    private LoginContract.Interactor mInteractor;

    private LoginPresenter<LoginContract.View, LoginContract.Interactor> mPresenter;

    private TestScheduler mTestScheduler;

    @Before
    public void before() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mPresenter = Mockito.spy(new LoginPresenter<>(
                mInteractor,
                testSchedulerProvider,
                compositeDisposable
        ));
        mPresenter.onAttach(mView);
    }

    private User getFakeUser() {
        return new User("email@email.com", "firstName", "lastName");
    }

    @Test
    public void onLoginClickShouldCallInteractor() {
        // given
        User user = getFakeUser();

        doReturn(Single.never())
                .when(mInteractor)
                .doLoginCall(user.getEmail(), "password");

        // when
        mPresenter.onLoginClick(user.getEmail(), "password");
        mTestScheduler.triggerActions();

        // then
        verify(mInteractor).doLoginCall(user.getEmail(), "password");
    }

    @Test
    public void onLoginClickShouldShowLoading() {
        // given
        User user = getFakeUser();
        doReturn(Single.never())
                .when(mInteractor)
                .doLoginCall(user.getEmail(), "password");

        // when
        mPresenter.onLoginClick(user.getEmail(), "password");
        mTestScheduler.triggerActions();

        // then
        verify(mView).onLoginStart(any(IBaseView.OnCancelListener.class));
    }

    @Test
    public void onLoginClickShouldUpdateViewOnSuccess() {
        // given
        User user = getFakeUser();

        doReturn(Single.just(user))
                .when(mInteractor)
                .doLoginCall(user.getEmail(), "password");

        // when
        mPresenter.onLoginClick(user.getEmail(), "password");
        mTestScheduler.triggerActions();

        // then
        verify(mView).onLoginSuccess(user);
    }

    @Test
    public void onLoginClickShouldUpdateViewOnFailure() {
        // given
        User user = getFakeUser();
        Error error = new Error();

        doReturn(Single.error(error))
                .when(mInteractor)
                .doLoginCall(user.getEmail(), "wrong");

        // when
        mPresenter.onLoginClick(user.getEmail(), "wrong");
        mTestScheduler.triggerActions();

        // then
        verify(mView).onLoginFailure(error);
    }



    @Test
    public void onLoginCancel() {
        // when
        mPresenter.onLoginCancel();

        // then
        assertTrue(mPresenter.getCompositeDisposable().isDisposed());
    }




    @After
    public void after() {
        mPresenter.onDetach();
    }

}
