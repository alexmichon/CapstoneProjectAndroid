package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.UserFactory;
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
 * Created by Alex on 21/01/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class RegisterPresenterTest {

    @Mock
    private RegisterContract.View mView;

    @Mock
    private RegisterContract.Interactor mInteractor;

    private RegisterPresenter<RegisterContract.View, RegisterContract.Interactor> mPresenter;

    private TestScheduler mTestScheduler;

    @Before
    public void before() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        mTestScheduler = new TestScheduler();
        TestSchedulerProvider testSchedulerProvider = new TestSchedulerProvider(mTestScheduler);
        mPresenter = Mockito.spy(new RegisterPresenter<>(
                mInteractor,
                testSchedulerProvider,
                compositeDisposable
        ));
        mPresenter.attachView(mView);
    }

    @Test
    public void onRegisterClickShouldCallInteractor() {
        // given
        User user = UserFactory.create();
        String password = "password";

        doReturn(Single.never())
                .when(mInteractor)
                .doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());

        // when
        mPresenter.onRegisterClick(user.getEmail(), password, password, user.getFirstName(), user.getLastName());
        mTestScheduler.triggerActions();

        // then
        verify(mInteractor).doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());
    }

    @Test
    public void onRegisterClickShouldShowLoading() {
        // given
        User user = UserFactory.create();
        String password = "password";

        doReturn(Single.never())
                .when(mInteractor)
                .doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());

        // when
        mPresenter.onRegisterClick(user.getEmail(), password, password, user.getFirstName(), user.getLastName());
        mTestScheduler.triggerActions();

        // then
        verify(mView).onAuthenticationStart(any(IBaseView.OnCancelListener.class));
    }

    @Test
    public void onRegisterClickShouldUpdateViewOnSuccess() {
        // given
        User user = UserFactory.create();
        String password = "password";

        doReturn(Single.just(user))
                .when(mInteractor)
                .doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());

        // when
        mPresenter.onRegisterClick(user.getEmail(), password, password, user.getFirstName(), user.getLastName());
        mTestScheduler.triggerActions();

        // then
        verify(mView).onAuthenticationSuccess(user);
    }

    @Test
    public void onRegisterClickShouldUpdateViewOnFailure() {
        // given
        User user = UserFactory.create();
        Error error = new Error();

        doReturn(Single.error(error))
                .when(mInteractor)
                .doRegister(user.getEmail(), "wrong", "wrong", user.getFirstName(), user.getLastName());

        // when
        mPresenter.onRegisterClick(user.getEmail(), "wrong", "wrong", user.getFirstName(), user.getLastName());
        mTestScheduler.triggerActions();

        // then
        verify(mView).onAuthenticationFailure(error);
    }



    @Test
    public void onRegisterCancel() {
        // when
        mPresenter.onAuthenticationCancel();

        // then
        assertTrue(mPresenter.getCompositeDisposable().isDisposed());
    }




    @After
    public void after() {
        mPresenter.detachView();
        mPresenter.destroy();
    }
}
