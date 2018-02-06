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
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.AuthenticationResponse;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.RegisterRequest;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.model.RegisterResponse;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
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
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_LOGIN)
                .addJSONObjectBody(new LoginRequest(email, password).toJson())
                .build()
                .getObjectObservable(AuthenticationResponse.class)
                .map(new Function<AuthenticationResponse, User>() {
                    @Override
                    public User apply(AuthenticationResponse authenticationResponse) throws Exception {
                        return authenticationResponse.get();
                    }
                })
                .singleOrError();
    }

    @Override
    public Single<User> doRegister(final String email, final String password, final String passwordConfirmation, final String firstName, final String lastName) {
        return Rx2AndroidNetworking.post(ApiEndPoint.ENDPOINT_REGISTER)
                .addJSONObjectBody(new RegisterRequest(email, password, passwordConfirmation, firstName, lastName).toJson())
                .build()
                .getObjectObservable(AuthenticationResponse.class)
                .singleOrError()
                .map(new Function<AuthenticationResponse, User>() {
                    @Override
                    public User apply(@NonNull AuthenticationResponse registerResponse) throws Exception {
                        return registerResponse.get();
                    }
                });
    }

    @Override
    public Single<User> doRestoreAuthentication(final Authentication authentication) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_VALIDATE_TOKEN)
                .build()
                .getObjectObservable(AuthenticationResponse.class)
                .map(new Function<AuthenticationResponse, User>() {
                    @Override
                    public User apply(AuthenticationResponse authenticationResponse) throws Exception {
                        return authenticationResponse.get();
                    }
                })
                .singleOrError();
    }

    @Override
    public Completable doLogout(final User user) {
        return Rx2AndroidNetworking.delete(ApiEndPoint.ENDPOINT_LOGOUT)
                .build()
                .getObjectObservable(Object.class)
                .singleOrError()
                .toCompletable();
    }
}
