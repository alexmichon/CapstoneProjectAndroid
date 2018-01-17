package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network;

import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.OkHttpResponseAndParsedRequestListener;
import com.androidnetworking.interfaces.OkHttpResponseListener;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.Authentication;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.user.User;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.ApiEndPoint;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.LoginRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.LoginResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.RegisterResponse;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by Alex on 20/11/2017.
 */

@Singleton
public class AuthService extends NetworkService implements IAuthService {

    @Inject
    public AuthService(OkHttpClient okHttpClient) {
        super(okHttpClient);
    }


    @Override
    public Single<User> doLogin(final String email, final String password) {
        return Single.create(new SingleOnSubscribe<User>() {
            @Override
            public void subscribe(@NonNull final SingleEmitter<User> e) throws Exception {
                Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN)
                        .addBodyParameter(new LoginRequest(email, password))
                        .build()
                        .getAsOkHttpResponseAndObject(LoginResponse.class, new OkHttpResponseAndParsedRequestListener() {
                            @Override
                            public void onResponse(Response okHttpResponse, Object response) {
                                LoginResponse loginResponse = (LoginResponse) response;
                                loginResponse.setHeaders(okHttpResponse.headers().toMultimap());
                                User user = loginResponse.getUser();
                                e.onSuccess(user);
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
    public Single<User> doRegister(final String email, final String password, final String passwordConfirmation, final String firstName, final String lastName) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_REGISTER)
                .addBodyParameter(new RegisterRequest(email, password, passwordConfirmation, firstName, lastName))
                .build()
                .getObjectObservable(RegisterResponse.class)
                .singleOrError()
                .map(new Function<RegisterResponse, User>() {
                    @Override
                    public User apply(@NonNull RegisterResponse registerResponse) throws Exception {
                        return registerResponse.getUser();
                    }
                });
    }

    @Override
    public Single<User> doRestoreAuthentication(final Authentication authentication) {
        return Single.create(new SingleOnSubscribe<User>() {
            @Override
            public void subscribe(@NonNull final SingleEmitter<User> singleEmitter) throws Exception {
                Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_VALIDATE_TOKEN)
                        .setOkHttpClient(getOkHttpClient())
                        .build()
                        .getAsOkHttpResponseAndObject(LoginResponse.class, new OkHttpResponseAndParsedRequestListener() {
                            @Override
                            public void onResponse(Response response, Object o) {
                                LoginResponse loginResponse = (LoginResponse) o;
                                loginResponse.setHeaders(response.headers().toMultimap());
                                User user = loginResponse.getUser();
                                singleEmitter.onSuccess(user);
                            }

                            @Override
                            public void onError(ANError anError) {
                                singleEmitter.onError(anError);
                            }
                        });
            }
        });
    }

    @Override
    public Completable doLogout(final User user) {
        return Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                Rx2AndroidNetworking.delete(ApiEndPoint.ENDPOINT_LOGOUT)
                        .build()
                        .getAsOkHttpResponse(new OkHttpResponseListener() {
                            @Override
                            public void onResponse(Response response) {

                            }

                            @Override
                            public void onError(ANError anError) {
                                Completable.error(anError);
                            }
                        });
            }
        });
    }
}
