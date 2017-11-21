package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterResponse;
import io.reactivex.Single;

/**
 * Created by Alex on 20/11/2017.
 */

public interface IAuthService {

    Single<LoginResponse> doLogin(LoginRequest request);
    Single<RegisterResponse> doRegister(RegisterRequest request);
}
