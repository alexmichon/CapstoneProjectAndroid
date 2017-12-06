package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;

/**
 * Created by Alex on 05/12/2017.
 */

public interface NetworkComponent {

    IAuthService authService();
    IExerciseService exerciseService();

}
