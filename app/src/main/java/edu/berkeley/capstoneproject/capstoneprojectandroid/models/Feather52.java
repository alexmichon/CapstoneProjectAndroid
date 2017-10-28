package edu.berkeley.capstoneproject.capstoneprojectandroid.models;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.Encoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.IMU;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.Sensor;

/**
 * Created by Alex on 25/10/2017.
 */

public class Feather52 {

    private static final String TAG = Feather52.class.getSimpleName();

    private final List<Sensor> mSensors = new ArrayList<>();
    private final IMU mIMU = new IMU();
    private final Encoder mEncoder = new Encoder();

    private final List<Exercise> mExercises = new ArrayList<>();

    private boolean mConnected;

    public Feather52() {
        mSensors.add(mIMU);
        mSensors.add(mEncoder);
    }

    public IMU getIMU() {
        return mIMU;
    }

    public Encoder getEncoder() {
        return mEncoder;
    }

    public List<Sensor> getSensors() {
        return mSensors;
    }

    public Sensor getSensor(int id) {
        for (Sensor s: mSensors) {
            if (s.getId() == id) {
                return s;
            }
        }

        return null;
    }

    public synchronized void setConnected(boolean connected) {
        mConnected = connected;
    }

    public synchronized boolean isConnected() {
        return mConnected;
    }

    public synchronized List<Exercise> getExercises() {
        return mExercises;
    }

    public synchronized void addExercise(Exercise exercise) {
        mExercises.add(exercise);
    }

    public synchronized Exercise getCurrentExercise() {
        Exercise e = mExercises.get(mExercises.size() - 1);
        if (!e.isCompleted()) {
            return e;
        }

        return null;
    }
}
