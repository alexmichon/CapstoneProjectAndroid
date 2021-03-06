package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHelper;

/**
 * Created by Alex on 30/12/2017.
 */

public class MeasurementManager implements IMeasurementManager {

    private final IApiHelper mApiHelper;

    @Inject
    public MeasurementManager(IApiHelper apiHelper) {
        mApiHelper = apiHelper;
    }

}
