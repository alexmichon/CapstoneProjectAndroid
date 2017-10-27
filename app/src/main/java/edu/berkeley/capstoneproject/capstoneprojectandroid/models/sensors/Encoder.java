package edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import edu.berkeley.capstoneproject.capstoneprojectandroid.activities.EncoderActivity;

/**
 * Created by Alex on 25/10/2017.
 */

public class Encoder extends Sensor<Float> {

    private static final String TAG = Encoder.class.getSimpleName();

    private static final String DEFAULT_NAME = "Encoder";

    public Encoder() {
        super(SensorType.ENCODER, DEFAULT_NAME);
    }

    public Encoder(String name) {
        super(SensorType.ENCODER, name);
    }
}
