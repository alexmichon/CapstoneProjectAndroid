package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.presenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginContract;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login.LoginPresenter;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

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

    User mUser = new User(mValidEmail, "", "");

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void loginShouldCallApi() {
        mPresenter.onLoginClick(mValidEmail, mValidPassword);
        // TODO
    }

    @Test
    public void loginShouldNotifyView() {
        mPresenter.onLoginClick(mValidEmail, mValidPassword);
        // TODO
    }

    @Test
    public void loginSuccessShouldUpdateView() {
        mPresenter.onLoginClick(mValidEmail, mValidPassword);
        // TODO
    }

    @Test
    public void loginFailureShouldUpdateView() {
        mPresenter.onLoginClick(mInvalidEmail, mInvalidPassword);
        // TODO
    }

    @After
    public void after() {
        Mockito.reset(mView);
    }

}
