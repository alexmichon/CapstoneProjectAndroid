package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import com.androidnetworking.error.ANError;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

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

    @Test
    public void loginShouldCallInteractor() {
        // given
        String email = "email@email.com";
        String password = "password";

        User user = new User(email, "first name", "last name");

        doReturn(Single.just(user))
                .when(mInteractor)
                .doLoginCall(new LoginRequest(email, password));

        // when
        mPresenter.onLoginClick(email, password);
        mTestScheduler.triggerActions();

        // then
        verify(mInteractor).doLoginCall(new LoginRequest(email, password));
    }

    @Test
    public void loginShouldShowLoading() {
        // given
        String email = "email@email.com";
        String password = "password";

        User user = new User(email, "first name", "last name");

        doReturn(Single.just(user))
                .when(mInteractor)
                .doLoginCall(new LoginRequest(email, password));

        // when
        mPresenter.onLoginClick(email, password);
        mTestScheduler.triggerActions();

        // then
        verify(mView).onLoginStart();
    }

    @Test
    public void loginShouldUpdateViewOnSuccess() {
        // given
        String email = "email@email.com";
        String password = "password";

        User user = new User(email, "first name", "last name");

        doReturn(Single.just(user))
                .when(mInteractor)
                .doLoginCall(new LoginRequest(email, password));

        // when
        mPresenter.onLoginClick(email, password);
        mTestScheduler.triggerActions();

        // then
        verify(mView).onLoginSuccess(user);
    }

    @Test
    public void loginShouldUpdateViewOnFailure() {
        // given
        String email = "email@email.com";
        String password = "password";

        Error error = new Error();

        doReturn(Single.error(error))
                .when(mInteractor)
                .doLoginCall(new LoginRequest(email, password));

        // when
        mPresenter.onLoginClick(email, password);
        mTestScheduler.triggerActions();

        // then
        verify(mView).onLoginFailure(error);
    }

    @After
    public void after() {
        mPresenter.onDetach();
    }

}
