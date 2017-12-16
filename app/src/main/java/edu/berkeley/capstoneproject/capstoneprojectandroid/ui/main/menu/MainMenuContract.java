package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseInteractor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBasePresenter;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.IBaseView;
import io.reactivex.Single;

/**
 * Created by Alex on 15/12/2017.
 */

public interface MainMenuContract {

    interface View extends IBaseView {

        void setUser(User user);

        void setSelectedItem(String title);
    }

    interface Interactor extends IBaseInteractor {

        Single<User> doGetCurrentUser();
    }

    interface Presenter<V extends View, I extends Interactor> extends IBasePresenter<V, I> {
        void onResume();

        List<MainMenuItem> getMenuItems();

        MainMenuItem getHomeItem();
    }
}
