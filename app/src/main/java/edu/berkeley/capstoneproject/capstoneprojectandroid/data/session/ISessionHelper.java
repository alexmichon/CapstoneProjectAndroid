package edu.berkeley.capstoneproject.capstoneprojectandroid.data.session;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IExerciseCreatorService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.ITrainingService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.session.IUserService;

/**
 * Created by Alex on 15/12/2017.
 */

public interface ISessionHelper {

    IUserService getUserService();
    IExerciseCreatorService getExerciseCreatorService();
    ITrainingService getTrainingService();
}
