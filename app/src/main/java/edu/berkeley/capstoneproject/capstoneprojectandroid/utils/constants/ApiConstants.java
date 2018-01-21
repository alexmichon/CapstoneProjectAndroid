package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.constants;

/**
 * Created by Alex on 09/11/2017.
 */

public class ApiConstants {

    public static final String HOST = "192.168.1.2";
    public static final int PORT = 3000;
    public static final String BASE_URL = HOST + ":" + String.valueOf(PORT);

    public static final String HTTP_URL = "http://" + BASE_URL;
    public static final String API_VERSION = "/api/v1";

    public static final String API_URL = HTTP_URL + API_VERSION;
    public static final String WEBSOCKET_URL = "ws://" + BASE_URL + "/cable";

    public static final int CACHE_SIZE = 10 * 1024 * 1024;

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;
}
