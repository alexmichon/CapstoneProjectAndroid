package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service.IExerciseService;

/**
 * Created by Alex on 18/11/2017.
 */

@Singleton
public interface IApiHelper {

    IAuthService getAuthService();
    IExerciseService getExerciseService();

    ApiHeader getApiHeader();
}
