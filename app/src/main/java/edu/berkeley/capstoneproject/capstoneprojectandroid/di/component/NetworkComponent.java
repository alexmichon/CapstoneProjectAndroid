package edu.berkeley.capstoneproject.capstoneprojectandroid.di.component;

import com.androidnetworking.interfaces.Parser;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IApiHeader;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.network.IAuthInterceptor;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IAuthService;
import edu.berkeley.capstoneproject.capstoneprojectandroid.service.network.IExerciseService;
import okhttp3.OkHttpClient;

/**
 * Created by Alex on 05/12/2017.
 */

public interface NetworkComponent {

    IApiHeader apiHeader();
    IAuthService authService();
    IExerciseService exerciseService();

    IAuthInterceptor authInterceptor();

    OkHttpClient okHttpClient();

    Parser.Factory parserFactory();
}
