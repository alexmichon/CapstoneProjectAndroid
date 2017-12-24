package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public interface IApiHelper {

    IAuthService getAuthService();
    IExerciseService getExerciseService();

    IApiHeader getApiHeader();
}
