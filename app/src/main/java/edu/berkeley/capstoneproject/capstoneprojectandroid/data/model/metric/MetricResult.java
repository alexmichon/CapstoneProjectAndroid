package edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Alex on 17/12/2017.
 */

public class MetricResult implements Parcelable {

    private final MetricGoal mMetricGoal;
    private final float mActual;
    private final float mExpected;
    private final boolean mResult;

    public MetricResult(MetricGoal metricGoal, float actual, float expected, boolean result) {
        mMetricGoal = metricGoal;
        mActual = actual;
        mExpected = expected;
        mResult = result;
    }

    public MetricGoal getMetricGoal() {
        return mMetricGoal;
    }

    public String getMetricName() {
        return mMetricGoal.getMetricName();
    }

    public boolean getResult() {
        return mResult;
    }

    public float getActual() {
        return mActual;
    }

    public float getExpected() {
        return mExpected;
    }




    protected MetricResult(Parcel in) {
        mMetricGoal = in.readParcelable(MetricGoal.class.getClassLoader());
        mActual = in.readFloat();
        mExpected = in.readFloat();
        mResult = in.readByte() != 0;
    }

    public static final Creator<MetricResult> CREATOR = new Creator<MetricResult>() {
        @Override
        public MetricResult createFromParcel(Parcel in) {
            return new MetricResult(in);
        }

        @Override
        public MetricResult[] newArray(int size) {
            return new MetricResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(mMetricGoal, i);
        parcel.writeFloat(mActual);
        parcel.writeFloat(mExpected);
        parcel.writeByte((byte) (mResult ? 1 : 0));
    }
}
