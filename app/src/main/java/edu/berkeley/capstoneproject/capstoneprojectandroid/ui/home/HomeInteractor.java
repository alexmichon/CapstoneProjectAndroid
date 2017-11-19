package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.home;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 11/11/2017.
 */

public class HomeInteractor extends BaseInteractor implements HomeContract.Interactor {

    @Inject
    public HomeInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
