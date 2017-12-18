package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;

/**
 * Created by Alex on 15/12/2017.
 */

public class MainMenuInteractor extends BaseInteractor implements MainMenuContract.Interactor {

    @Inject
    public MainMenuInteractor(IDataManager dataManager) {
        super(dataManager);
    }

    @Override
    public Single<User> doGetCurrentUser() {
        return Single.just(getDataManager().getSessionHelper().getUserService().getCurrentUser());
    }
}
