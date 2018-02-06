package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.login;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.UserFactory;
import io.reactivex.Single;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 21/11/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class LoginInteractorTest {

    private LoginInteractor mInteractor;

    @Mock
    private IAuthManager mAuthManager;

    @Before
    public void before() {
        mInteractor = new LoginInteractor(mAuthManager);
    }

    @Test
    public void loginShouldCallApi() {
        // given
        User user = UserFactory.create();
        doReturn(Single.just(user)).when(mAuthManager).doLogin(user.getEmail(), "password");

        //  when
        mInteractor.doLogin(user.getEmail(), "password");

        // then
        verify(mAuthManager).doLogin(user.getEmail(), "password");
    }
}
