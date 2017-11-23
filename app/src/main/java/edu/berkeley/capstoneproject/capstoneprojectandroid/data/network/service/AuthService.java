package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.service;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiEndPoint;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.model.RegisterResponse;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import okhttp3.Response;

/**
 * Created by Alex on 20/11/2017.
 */

@Singleton
public class AuthService implements IAuthService {

    @Inject
    public AuthService() {

    }


    @Override
    public Single<LoginResponse> doLogin(final LoginRequest request) {
        return Single.create(new SingleOnSubscribe<LoginResponse>() {
            @Override
            public void subscribe(@NonNull final SingleEmitter<LoginResponse> e) throws Exception {
                Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN)
                        .addBodyParameter(request)
                        .build()
                        .getAsOkHttpResponseAndObject(LoginResponse.class, new OkHttpResponseAndParsedRequestListener() {
                            @Override
                            public void onResponse(Response okHttpResponse, Object response) {
                                LoginResponse loginResponse = (LoginResponse) response;
                                loginResponse.setHeaders(okHttpResponse.headers().toMultimap());
                                e.onSuccess(loginResponse);
                            }

                            @Override
                            public void onError(ANError anError) {
                                e.onError(anError);
                            }
                        });
            }
        });
    }

    @Override
    public Single<RegisterResponse> doRegister(RegisterRequest request) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_REGISTER)
                .addBodyParameter(request)
                .build()
                .getObjectObservable(RegisterResponse.class)
                .singleOrError();
    }
}
