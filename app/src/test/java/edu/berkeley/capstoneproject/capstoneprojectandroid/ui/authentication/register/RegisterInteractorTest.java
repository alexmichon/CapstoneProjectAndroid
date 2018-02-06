package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.authentication.register;

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
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 21/01/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class RegisterInteractorTest {

    private RegisterInteractor mInteractor;

    @Mock
    private IAuthManager mAuthManager;

    @Before
    public void before() {
        mInteractor = new RegisterInteractor(mAuthManager);
    }

    @Test
    public void loginShouldCallApi() {
        // given
        User user = UserFactory.create();
        String password = "password";
        doReturn(Single.just(user)).when(mAuthManager).doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());

        //  when
        mInteractor.doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());

        // then
        verify(mAuthManager).doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());
    }

}
