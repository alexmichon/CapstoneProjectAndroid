package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.auth.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.models.User;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Alex on 07/11/2017.
 */

public interface AuthService {

    @POST("/api/v1/auth/sign_in")
    Observable<User> login(@Body LoginRequest request);

    @POST("/api/v1/auth")
    Observable<User> register(@Body RegisterRequest request);
}
