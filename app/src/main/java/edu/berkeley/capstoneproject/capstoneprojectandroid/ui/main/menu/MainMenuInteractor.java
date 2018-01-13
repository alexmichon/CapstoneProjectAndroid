package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main.menu;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Single;

/**
 * Created by Alex on 15/12/2017.
 */

public class MainMenuInteractor extends BaseInteractor implements MainMenuContract.Interactor {

    private final IAuthManager mAuthManager;

    @Inject
    public MainMenuInteractor(IDataManager dataManager, IAuthManager authManager) {
        super(dataManager);
        mAuthManager = authManager;
    }

    @Override
    public User getCurrentUser() {
        return mAuthManager.getCurrentUser();
    }
}
