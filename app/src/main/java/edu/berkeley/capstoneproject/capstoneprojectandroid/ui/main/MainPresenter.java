package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.view.MenuItem;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu.MainMenuItem;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 08/11/2017.
 */

public class MainPresenter<V extends MainContract.View, I extends MainContract.Interactor>
        extends BasePresenter<V, I> implements MainContract.Presenter<V, I> {

    @Inject
    public MainPresenter(I interactor,
                         ISchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }


    @Override
    public void onMainMenuItemClick(MainMenuItem item) {
        switch (item.getTitle()) {
            case MainMenuItem.HOME_TITLE:
                if (isViewAttached()) {
                    getView().showHomeFragment();
                }
                break;
            case MainMenuItem.LOGOUT_TITLE:
                logout();
                break;
            default:
                if (isViewAttached()) {
                    getView().showError("Not implemented yet !");
                }
                break;
        }
    }

    protected void logout() {
        if (isViewAttached()) {
            getView().showLoading();
        }

        getCompositeDisposable().add(getInteractor().doLogout()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (isViewAttached()) {
                            getView().moveToAuthenticationActivity();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (isViewAttached()) {
                            getView().hideLoading();
                            getView().showError(throwable);
                        }
                    }
                })
        );
    }
}
