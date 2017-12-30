package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user;

import java.util.concurrent.Callable;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.interceptor.AuthInterceptor;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 30/12/2017.
 */

@Singleton
public class AuthManager implements IAuthManager {

    private final IApiHelper mApiHelper;
    private final IPreferencesHelper mPreferencesHelper;

    private User mCurrentUser;

    @Inject
    public AuthManager(IApiHelper apiHelper, IPreferencesHelper preferencesHelper) {
        mApiHelper = apiHelper;
    }

    @Override
    public Single<User> login(String email, String password) {
        return mApiHelper.getAuthService().doLogin(email, password).doOnSuccess(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                onSuccess(user);
            }
        });
    }

    @Override
    public Single<User> register(String email, String password, String passwordConfirmation, String firstName, String lastName) {
        return mApiHelper.getAuthService().doRegister(email, password, passwordConfirmation, firstName, lastName)
                .doOnSuccess(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        onSuccess(user);
                    }
                });
    }

    private void onSuccess(User user) {
        user.setAuthentication(mApiHelper.getAuthInterceptor().getAuthentication());
        mCurrentUser = user;
    }

    @Override
    public Completable logout() {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                mApiHelper.getAuthInterceptor().setAuthentication(null);
                mPreferencesHelper.removeAuthentication();
                remember(false);
                mCurrentUser = null;
            }
        });
    }

    @Override
    public User getCurrentUser() {
        return mCurrentUser;
    }

    @Override
    public Single<User> restore() {
        return Single.fromCallable(new Callable<User>() {
            @Override
            public User call() throws Exception {
                return mPreferencesHelper.getAuthentication()
            }
        }).flatMap(new Function<Authentication, SingleSource<User>>() {
            @Override
            public SingleSource<User> apply(@NonNull Authentication authentication) throws Exception {
                mApiHelper.getAuthService().doRestoreAuthentication(authentication)
            }
        }).doOnSuccess(new Consumer<User>() {
            @Override
            public void accept(User user) throws Exception {
                mCurrentUser = user;
                remember(true);
            }
        });
    }

    private void save(Authentication authentication) {
        mPreferencesHelper.setAuthentication(authentication);
    }

    @Override
    public void remember(boolean enabled) {
        if (enabled) {
            mApiHelper.getAuthInterceptor().setListener(new AuthInterceptor.Listener() {
                @Override
                public void onAuthUpdate(Authentication auth) {
                    save(auth);
                }
            });
        }
        else {
            mApiHelper.getAuthInterceptor().setListener(null);
        }
    }
}
