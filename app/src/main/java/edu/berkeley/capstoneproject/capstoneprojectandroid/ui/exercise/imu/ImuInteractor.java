package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.imu;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.IDataManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseInteractor;

/**
 * Created by Alex on 17/11/2017.
 */

public class ImuInteractor extends BaseInteractor implements ImuContract.Interactor {

    @Inject
    public ImuInteractor(IDataManager dataManager) {
        super(dataManager);
    }
}
