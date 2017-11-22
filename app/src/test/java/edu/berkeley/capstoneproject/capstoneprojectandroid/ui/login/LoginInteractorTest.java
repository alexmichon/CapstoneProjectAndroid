package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.login;

import org.junit.Before;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.DataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services.IAuthService;
import io.reactivex.Single;
import io.reactivex.subscribers.TestSubscriber;

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

    @Test
    public void loginShouldCallApi() {
        String email = "email";
        String password = "password";

        doReturn(Single.just(new LoginResponse())).when(mAuthService).doLogin(new LoginRequest(email, password));

        mInteractor.doLoginCall(new LoginRequest(email, password));

        verify(mAuthService).doLogin(new LoginRequest(email, password));
    }

    @Test
    public void loginShouldSetHeadersOnSuccess() {
        String email = "email";
        String password = "password";

        LoginResponse response = mock(LoginResponse.class);
        String accessToken = "access-token";
        String client = "client";
        String expiry = "expiry";
        String tokenType = "token-type";
        String uid = "uid";

        doReturn(Single.just(response)).when(mAuthService).doLogin(new LoginRequest(email, password));

        doReturn(accessToken).when(response).getAccessToken();
        doReturn(client).when(response).getClient();
        doReturn(expiry).when(response).getExpiry();
        doReturn(tokenType).when(response).getTokenType();
        doReturn(uid).when(response).getUid();

        mInteractor.doLoginCall(new LoginRequest(email, password)).test();

        verify(mApiHeader).setAccessToken(accessToken);
        verify(mApiHeader).setClient(client);
        verify(mApiHeader).setExpiry(expiry);
        verify(mApiHeader).setTokenType(tokenType);
        verify(mApiHeader).setUid(uid);
    }
}
