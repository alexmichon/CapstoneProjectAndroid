package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.RegisterResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import io.reactivex.Single;

/**
 * Created by Alex on 28/11/2017.
 */

public class AuthService implements IAuthService {

    @Inject
    public AuthService() {

    }

    @Override
    public Single<LoginResponse> doLogin(LoginRequest request) {
        return null;
    }

    @Override
    public Single<RegisterResponse> doRegister(RegisterRequest request) {
        return null;
    }
}
