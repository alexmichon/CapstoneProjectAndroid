package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import timber.log.Timber;

/**
 * Created by Alex on 07/11/2017.
 */

public class SplashPresenter<V extends SplashContract.View, I extends SplashContract.Interactor>
        extends BasePresenter<V, I> implements SplashContract.Presenter<V, I> {

    private ConnectivityManager mConnectivityManager;

    private Authentication mAuthentication;
    private boolean mIsLoggedIn = false;

    @Inject
    public SplashPresenter(I interactor,
                           ISchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable,
                           ConnectivityManager connectivityManager) {
        super(interactor, schedulerProvider, compositeDisposable);
        mConnectivityManager = connectivityManager;
    }

    @Override
    public void onStart() {
        getCompositeDisposable().add(
                getNetworkStateCompletable().andThen(
                    getAuthenticationCompletable())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        onStartDone();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        onStartError(throwable);
                    }
                })
        );
    }

    protected Completable getNetworkStateCompletable() {
        return getInteractor().doCheckNetworkState().doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {
                if (isViewAttached()) {
                    getView().updateMessage("Checking network state...");
                }
            }
        }).flatMapCompletable(new Function<NetworkInfo, CompletableSource>() {
            @Override
            public CompletableSource apply(@NonNull NetworkInfo networkInfo) throws Exception {
                if (networkInfo == null || networkInfo.getState() != NetworkInfo.State.CONNECTED) {
                    return Completable.error(new IllegalStateException());
                }

                return Completable.complete();
            }
        })
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui());
    }

    public void checkNetworkState() {
        Timber.d("Checking network state");

        if (isViewAttached()) {
            getView().updateMessage("Checking network state...");
        }

        NetworkInfo info = mConnectivityManager.getActiveNetworkInfo();
        if (info == null || info.getState() != NetworkInfo.State.CONNECTED) {
            getView().stop();
        }
    }

    protected Completable getAuthenticationCompletable() {
        return getStoredAuthenticationSingle().flatMap(new Function<Authentication, SingleSource<User>>() {
            @Override
            public SingleSource<User> apply(@NonNull Authentication authentication) throws Exception {
                mAuthentication = authentication;
                return getRestoreAuthenticationSingle(authentication);
            }
        }).flatMapCompletable(new Function<User, CompletableSource>() {
            @Override
            public CompletableSource apply(@NonNull final User user) throws Exception {
                return Completable.fromAction(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (user == null || !user.isAuthenticated()) {
                            mIsLoggedIn = false;
                            return;
                        }
                        mIsLoggedIn = true;
                        getInteractor().setCurrentUser(user);
                    }
                });
            }
        });
    }



    protected Single<Authentication> getStoredAuthenticationSingle() {
        return getInteractor().doGetStoredAuthentication()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui());
    }

    protected Single<User> getRestoreAuthenticationSingle(Authentication authentication) {
        return getInteractor().doRestoreAuthentication(authentication)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui());
    }


    protected void getStoredAuthentication() {
        if (isViewAttached()) {
            getView().updateMessage("Loading authentication credentials");
        }

        getCompositeDisposable().add(getInteractor().doGetStoredAuthentication()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Authentication>() {
                    @Override
                    public void accept(Authentication authentication) throws Exception {
                        mAuthentication = authentication;
                        if (mAuthentication.isValid()) {
                            restoreAuthentication(authentication);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mIsLoggedIn = false;
                    }
                })
        );
    }

    protected void restoreAuthentication(Authentication authentication) {
        if (isViewAttached()) {
            getView().updateMessage("Restoring credentials");
        }

        getCompositeDisposable().add(getInteractor().doRestoreAuthentication(authentication)
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        mIsLoggedIn = true;
                        getInteractor().setCurrentUser(user);
                        onStartDone();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mIsLoggedIn = false;
                        onStartDone();
                    }
                })
        );
    }

    protected void onStartDone() {
        if (isViewAttached()) {
            getView().done();

            if (!mIsLoggedIn) {
                getView().moveToAuthenticationActivity(mAuthentication);
            }
            else {
                getView().moveToMainActivity();
            }
        }
    }


    protected void onStartError(Throwable throwable) {
        if (isViewAttached()) {
            getView().showError(throwable);
            getView().stop();
        }
    }

}
