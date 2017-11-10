package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginPresenter;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 09/11/2017.
 */

public class LoginPresenterSuite {

    @Mock
    private AuthService mAuthService;

    @Mock
    private LoginContract.View mView;

    private LoginPresenter mPresenter;

    String mValidEmail = "email@email.com";
    String mValidPassword = "password";

    String mInvalidEmail = "email@email.com";
    String mInvalidPassword = "password";

    User mUser = new User(mValidEmail, mValidPassword, "", "");

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new LoginPresenter(mView, mAuthService);

        Scheduler testScheduler = new TestScheduler();
        mPresenter.setObservingScheduler(testScheduler);
        mPresenter.setSubscribingScheduler(testScheduler);
    }

    @Test
    public void loginShouldCallApi() {
        when(mAuthService.login(any(LoginRequest.class))).thenReturn(Observable.just(mUser));
        mPresenter.login(mValidEmail, mValidPassword);
        verify(mAuthService).login(any(LoginRequest.class));
    }

    @Test
    public void loginShouldNotifyView() {
        when(mAuthService.login(any(LoginRequest.class))).thenReturn(Observable.just(mUser));
        mPresenter.login(mValidEmail, mValidPassword);
        verify(mView).onLoginTry();
    }

    @Test
    public void loginSuccessShouldUpdateView() {
        when(mAuthService.login(any(LoginRequest.class))).thenReturn(Observable.just(mUser));
        mPresenter.login(mValidEmail, mValidPassword);
        verify(mView).onLoginSuccess(mUser);
    }

    @Test
    public void loginFailureShouldUpdateView() {
        when(mAuthService.login(any(LoginRequest.class))).thenReturn(Observable.<User>error(new Exception()));
        mPresenter.login(mInvalidEmail, mInvalidPassword);
        verify(mView).onLoginFailure();
    }

    @After
    public void after() {
        Mockito.reset(mView);
    }

}
