package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.services;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.models.RegisterResponse;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Alex on 07/11/2017.
 */

public interface AuthService {

    @POST("/api/v1/auth/sign_in")
    Observable<LoginResponse> doLoginApiCall(@Body LoginRequest request);

    @POST("/api/v1/auth")
    Observable<RegisterResponse> doRegisterApiCall(@Body RegisterRequest request);
}
