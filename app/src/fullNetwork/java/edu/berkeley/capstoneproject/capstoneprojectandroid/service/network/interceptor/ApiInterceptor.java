package edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.interceptor;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;

import edu.berkeley.capstoneproject.capstoneprojectandroid.BuildConfig;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okio.Buffer;
import timber.log.Timber;

/**
 * Created by Alex on 24/12/2017.
 */

@Singleton
public class ApiInterceptor implements Interceptor {

    @Inject
    public ApiInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request newRequest = chain.request().newBuilder()
                .addHeader("content-type", "application/json")
                .addHeader("accept", "application/json")
                .build();

        if (BuildConfig.DEBUG) {
            logRequest(newRequest);
        }

        return chain.proceed(newRequest);
    }

    private void logRequest(Request request) {
        if (request.body() != null) {
            try {
                final Request copy = request.newBuilder().build();
                final Buffer buffer = new Buffer();
                copy.body().writeTo(buffer);
                Timber.d("Request body: %s", buffer.readUtf8());
            } catch (final IOException e) {
                Timber.w(e, "Failed to stringify request body");
            }
        }
    }
}
