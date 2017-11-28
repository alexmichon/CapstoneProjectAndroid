package edu.berkeley.capstoneproject.capstoneprojectandroid.service.bluetooth;

import java.util.List;

import javax.inject.Inject;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Alex on 18/11/2017.
 */

public class MeasurementService extends BaseService implements IMeasurementService {

    private Observable<byte[]> mEncoderObservable;
    private Observable<byte[]> mImuObservable;

    @Inject
    public MeasurementService() {
        // Empty constructor
    }

    @Override
    public Observable<Measurement> getEncoderObservable() {
        return mEncoderObservable.map(new Function<byte[], List<Measurement>>() {
            @Override
            public List<Measurement> apply(@NonNull byte[] bytes) throws Exception {
                return Measurement.decodeEncoder(bytes);
            }
        }).flatMap(new Function<List<Measurement>, ObservableSource<Measurement>>() {
            @Override
            public ObservableSource<Measurement> apply(@NonNull List<Measurement> measurements) throws Exception {
                return Observable.fromIterable(measurements);
            }
        });
    }

    @Override
    public void setEncoderObservable(Observable<byte[]> encoderObservable) {
        mEncoderObservable = encoderObservable;
    }

    @Override
    public Observable<Measurement> getImuObservable() {
        return mImuObservable.map(new Function<byte[], List<Measurement>>() {
            @Override
            public List<Measurement> apply(@NonNull byte[] bytes) throws Exception {
                return Measurement.decodeImu(bytes);
            }
        }).flatMap(new Function<List<Measurement>, ObservableSource<Measurement>>() {
            @Override
            public ObservableSource<Measurement> apply(@NonNull List<Measurement> measurements) throws Exception {
                return Observable.fromIterable(measurements);
            }
        });
    }

    @Override
    public void setImuObservable(Observable<byte[]> imuObservable) {
        mImuObservable = imuObservable;
    }
}
