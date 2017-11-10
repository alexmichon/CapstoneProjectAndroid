package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.presenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginPresenter;
import io.reactivex.Observable;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Alex on 09/11/2017.
 */

public class LoginPresenterSuiteSuccess {

    @Mock
    private AuthService mAuthService;

    @Mock
    private LoginContract.View mView;

    private LoginPresenter mPresenter;

    String mValidEmail = "email@email.com";
    String mValidPassword = "password";
    User mUser = new User(mValidEmail, mValidPassword, "", "");

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new LoginPresenter(mView, mAuthService);
        when(mAuthService.login(any(LoginRequest.class))).thenReturn(Observable.just(mUser));
    }

    @Test
    public void loginShouldCallApi() {
        mPresenter.login(mValidEmail, mValidPassword);
        verify(mAuthService).login(any(LoginRequest.class));
    }

    @Test
    public void loginShouldNotifyView() {
        mPresenter.login(mValidEmail, mValidPassword);
        verify(mView).onLoginTry();
    }

    @Test
    public void loginSuccessShouldUpdateView() {
        mPresenter.login(mValidEmail, mValidPassword);
        verify(mView).onLoginSuccess(mUser);
    }
}
