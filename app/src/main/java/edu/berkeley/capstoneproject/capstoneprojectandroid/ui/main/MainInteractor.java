package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.main;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.IAuthManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;
import io.reactivex.Completable;

/**
 * Created by Alex on 10/11/2017.
 */

public class MainInteractor extends BaseInteractor implements MainContract.Interactor {

    private final IAuthManager mAuthManager;

    @Inject
    public MainInteractor(IAuthManager authManager) {
        mAuthManager = authManager;
    }

    @Override
    public Completable doLogout() {
        return mAuthManager.doLogout();
    }
}
