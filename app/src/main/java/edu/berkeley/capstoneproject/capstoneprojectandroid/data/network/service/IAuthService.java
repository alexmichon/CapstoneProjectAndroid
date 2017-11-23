package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.RegisterResponse;
import io.reactivex.Single;

/**
 * Created by Alex on 20/11/2017.
 */

public interface IAuthService {

    Single<LoginResponse> doLogin(LoginRequest request);
    Single<RegisterResponse> doRegister(RegisterRequest request);
}
