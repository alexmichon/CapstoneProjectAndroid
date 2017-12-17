package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.utils.rx.ISchedulerProvider;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Alex on 15/12/2017.
 */

public class MainMenuPresenter<V extends MainMenuContract.View, I extends MainMenuContract.Interactor> extends BasePresenter<V, I> implements MainMenuContract.Presenter<V, I> {

    private MainMenuItem mHomeItem = new MainMenuItem(MainMenuItem.HOME_TITLE, MainMenuItem.HOME_ICON);
    private MainMenuItem mLogOutItem = new MainMenuItem(MainMenuItem.LOGOUT_TITLE, MainMenuItem.LOGOUT_ICON);

    @Inject
    public MainMenuPresenter(I interactor, ISchedulerProvider schedulerProvider, CompositeDisposable compositeDisposable) {
        super(interactor, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onResume() {
        getCompositeDisposable().add(getInteractor().doGetCurrentUser()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<User>() {
                    @Override
                    public void accept(User user) throws Exception {
                        if (isViewAttached()) {
                            getView().setUser(user);
                        }
                    }
                })
        );
    }

    @Override
    public List<MainMenuItem> getMenuItems() {
        List<MainMenuItem> menuItems = new ArrayList<>();

        menuItems.add(mHomeItem);
        menuItems.add(mLogOutItem);

        return menuItems;
    }

    @Override
    public MainMenuItem getHomeItem() {
        return mHomeItem;
    }

    @Override
    public MainMenuItem getLogOutItem() {
        return mLogOutItem;
    }
}
