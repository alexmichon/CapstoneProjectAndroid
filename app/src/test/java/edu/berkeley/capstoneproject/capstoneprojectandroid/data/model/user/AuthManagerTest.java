package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IAuthInterceptor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.pref.IPreferencesHelper;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.AuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import io.reactivex.Completable;
import io.reactivex.Single;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Created by Alex on 21/01/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class AuthManagerTest {

    private AuthManager mAuthManager;

    @Mock
    private IApiHelper mApiHelper;

    @Mock
    private IAuthService mAuthService;

    @Mock
    private IPreferencesHelper mPrefHelper;

    @Mock
    private IAuthInterceptor mAuthInterceptor;

    @Before
    public void before() {
        doReturn(mAuthService).when(mApiHelper).getAuthService();

        mAuthManager = new AuthManager(mApiHelper, mPrefHelper, mAuthInterceptor);
    }


    @Test
    public void whenLoginShouldCallApiHelper() {
        // given
        User user = UserFactory.create();
        String password = "password";
        doReturn(Single.just(user)).when(mAuthService).doLogin(user.getEmail(), password);

        // when
        mAuthManager.doLogin(user.getEmail(), password);

        // then
        verify(mAuthService).doLogin(user.getEmail(), password);
    }

    @Test
    public void whenLoginShouldSetAuthentication() {
        // given
        User user = UserFactory.create();
        String password = "password";
        doReturn(Single.just(user)).when(mAuthService).doLogin(user.getEmail(), password);

        Authentication authentication = AuthenticationFactory.create();
        doReturn(authentication).when(mAuthInterceptor).getAuthentication();

        // when
        mAuthManager.doLogin(user.getEmail(), password).test();

        // then
        assertEquals(user, mAuthManager.getCurrentUser());
        assertEquals(authentication, user.getAuthentication());
    }

    @Test
    public void whenRegisterShouldCallApiHelper() {
        // given
        User user = UserFactory.create();
        String password = "password";
        doReturn(Single.just(user)).when(mAuthService).doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());

        // when
        mAuthManager.doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());

        // then
        verify(mAuthService).doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());
    }

    @Test
    public void whenRegisterShouldSetAuthentication() {
        // given
        User user = UserFactory.create();
        String password = "password";
        doReturn(Single.just(user)).when(mAuthService).doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName());

        Authentication authentication = AuthenticationFactory.create();
        doReturn(authentication).when(mAuthInterceptor).getAuthentication();

        // when
        mAuthManager.doRegister(user.getEmail(), password, password, user.getFirstName(), user.getLastName()).test();

        // then
        assertEquals(user, mAuthManager.getCurrentUser());
        assertEquals(authentication, user.getAuthentication());
    }

    @Test
    public void whenLogoutShouldCallApiHelper() {
        // given
        User user = UserFactory.create();
        mAuthManager.setCurrentUser(user);

        doReturn(Completable.complete()).when(mAuthService).doLogout(user);

        // when
        mAuthManager.doLogout();

        // then
        verify(mAuthService).doLogout(user);
    }

    @Test
    public void whenLogoutShouldRemoveAuthentication() {
        // given
        User user = UserFactory.create();
        mAuthManager.setCurrentUser(user);

        doReturn(Completable.complete()).when(mAuthService).doLogout(user);

        // when
        mAuthManager.doLogout().test();

        // then
        assertNull(mAuthManager.getCurrentUser());
        verify(mPrefHelper).removeAuthentication();
        verify(mAuthInterceptor).setAuthentication(null);
        verify(mAuthInterceptor).setListener(null);
    }
}
