package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor;

/**
 * Created by Alex on 19/11/2017.
 */

public class SensorManager {

    public static final short ID_ENCODER = 1;
    public static final short ID_ACCELEROMETER = 2;
    public static final short ID_GYROSCOPE = 3;

    private static final String NAME_ACCELEROMETER = "Accelerometer";
    private static final String NAME_GYROSCOPE = "Gyroscope";
    private static final String NAME_ENCODER = "Encoder";

    private static final Sensor[] SENSORS = {
            new Accelerometer(ID_ACCELEROMETER, NAME_ACCELEROMETER),
            new Gyroscope(ID_GYROSCOPE, NAME_GYROSCOPE),
            new Encoder(ID_ENCODER, NAME_ENCODER)
    };

    public static Sensor find(int id) {
        for (Sensor s: SENSORS) {
            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }
}
