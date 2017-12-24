package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;

/**
 * Created by Alex on 05/12/2017.
 */

public interface NetworkComponent {

    IApiHeader apiHeader();
    IAuthService authService();
    IExerciseService exerciseService();

}
