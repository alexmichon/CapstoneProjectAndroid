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
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.TestSchedulerProvider;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.TestScheduler;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 09/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginPresenterTest {

    @Mock
    private AuthService mAuthService;

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
        mPresenter = new LoginPresenter<>(
                mInteractor,
                testSchedulerProvider,
                compositeDisposable
        );
        mPresenter.onAttach(mView);
    }

    @Test
    public void loginShouldCallApi() {
        String email = "email@email.com";
        String password = "password";

        User user = new User(email, "first name", "last name");

        doReturn(Single.just(user))
                .when(mInteractor)
                .doLoginCall(new LoginRequest(email, password));

        mTestScheduler.triggerActions();

        mPresenter.onLoginClick(email, password);
        verify(mInteractor).doLoginCall(new LoginRequest(email, password));
    }

    @Test
    public void loginShouldNotifyView() {
        String email = "email@email.com";
        String password = "password";

        User user = new User(email, "first name", "last name");

        doReturn(Single.just(user))
                .when(mInteractor)
                .doLoginCall(new LoginRequest(email, password));

        mTestScheduler.triggerActions();

        mPresenter.onLoginClick(email, password);
        verify(mView).showLoading();
    }

    @Test
    public void loginSuccessShouldUpdateView() {
        String email = "email@email.com";
        String password = "password";

        User user = new User(email, "first name", "last name");

        doReturn(Single.just(user))
                .when(mInteractor)
                .doLoginCall(new LoginRequest(email, password));

        mPresenter.onLoginClick(email, password);

        mTestScheduler.triggerActions();

        verify(mView).hideLoading();
        verify(mView).onLoginSuccess(user);
    }

    @Test
    public void loginFailureShouldUpdateView() {
        String email = "email@email.com";
        String password = "password";

        User user = new User(email, "first name", "last name");

        doReturn(Single.error(new ANError()))
                .when(mInteractor)
                .doLoginCall(new LoginRequest(email, password));

        mPresenter.onLoginClick(email, password);

        mTestScheduler.triggerActions();

        verify(mView).hideLoading();
        verify(mView).onError(anyString());
    }

    @After
    public void after() {
        mPresenter.onDetach();
    }

}
