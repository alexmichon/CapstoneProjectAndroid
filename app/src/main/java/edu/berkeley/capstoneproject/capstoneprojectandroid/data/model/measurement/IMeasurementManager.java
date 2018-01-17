package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.measurement;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import io.reactivex.Completable;

/**
 * Created by Alex on 30/12/2017.
 */

public interface IMeasurementManager {

    Completable doSave(Measurement measurement);
}
