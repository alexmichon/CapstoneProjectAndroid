package edu.berkeley.capstoneproject.capstoneprojectandroid.data.network;

import edu.berkeley.capstoneproject.capstoneprojectandroid.constants.ApiConstants;

/**
 * Created by Alex on 20/11/2017.
 */

public class ApiEndPoint {

    public static final String ENDPOINT_LOGIN = ApiConstants.API_URL
            + "/auth/sign_in";

    public static final String ENDPOINT_REGISTER = ApiConstants.API_URL
            + "/auth";

    public static final String ENDPOINT_EXERCISES = ApiConstants.API_URL
            + "/exercises";

    public static final String ENDPOINT_MEASUREMENTS = ApiConstants.API_URL
            + "/exercises/{exercise_id}/measurements";
}