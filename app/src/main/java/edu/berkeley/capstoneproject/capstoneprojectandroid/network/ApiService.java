package edu.berkeley.capstoneproject.capstoneprojectandroid.network;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.users.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Alex on 07/11/2017.
 */

public interface ApiService {

    @POST("/api/v1/auth/sign_in")
    Call<User> login(@Body LoginRequest request);

    @POST("/api/v1/auth")
    Call<User> register(@Body RegisterRequest request);
}
