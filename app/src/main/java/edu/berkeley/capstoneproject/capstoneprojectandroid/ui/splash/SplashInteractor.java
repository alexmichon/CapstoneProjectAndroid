package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.splash;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 10/11/2017.
 */

public class SplashInteractor extends BaseInteractor implements SplashContract.Interactor {

    @Inject
    public SplashInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
