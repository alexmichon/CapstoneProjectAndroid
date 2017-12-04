package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
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
    private IDataManager mDataManager;

    @Mock
    private IApiHelper mApiHelper;

    @Mock
    private IAuthService mAuthService;

    @Mock
    private ApiHeader mApiHeader;

    @Before
    public void before() {
        doReturn(mApiHelper).when(mDataManager).getApiHelper();
        doReturn(mAuthService).when(mApiHelper).getAuthService();
        doReturn(mApiHeader).when(mApiHelper).getApiHeader();

        mInteractor = new LoginInteractor(mDataManager);
    }

    private User getFakeUser() {
        return new User("email@email.com", "firstName", "lastName");
    }

    @Test
    public void loginShouldCallApi() {
        // given
        User user = getFakeUser();

        doReturn(Single.just(user)).when(mAuthService).doLogin(user.getEmail(), "password");

        //  when
        mInteractor.doLoginCall(user.getEmail(), "password");

        // then
        verify(mAuthService).doLogin(user.getEmail(), "password");
    }

    @Test
    public void loginShouldSetHeadersOnSuccess() {
        // TODO
        // given

        // when
        // mInteractor.doLoginCall(new LoginRequest(email, password)).test();

        // then
        //verify(mApiHeader).setAccessToken(accessToken);
        //verify(mApiHeader).setClient(client);
        //verify(mApiHeader).setExpiry(expiry);
        //verify(mApiHeader).setTokenType(tokenType);
        //verify(mApiHeader).setUid(uid);
    }
}
