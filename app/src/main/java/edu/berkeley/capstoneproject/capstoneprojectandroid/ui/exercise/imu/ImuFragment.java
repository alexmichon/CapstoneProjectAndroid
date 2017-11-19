package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise.imu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.Encoder;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.sensors.IMU;
import edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;

import static edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service.EXTRA_LABEL;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service.EXTRA_TOOK_AT;
import static edu.berkeley.capstoneproject.capstoneprojectandroid.services.Feather52Service.EXTRA_VALUE;

/**
 * Created by Alex on 17/11/2017.
 */

public class ImuFragment extends BaseFragment implements ImuContract.View {

    private static final String TAG = ImuFragment.class.getSimpleName();

    private static final String TITLE = "IMU";

    @Override
    public String getTitle() {
        return TITLE;
    }

    private View mView;
    private LineChart mAccView;
    private LineChart mGyrView;
    private LineChart mEncoderView;

    private int[] mColors = new int[] {
            ColorTemplate.COLORFUL_COLORS[0],
            ColorTemplate.COLORFUL_COLORS[1],
            ColorTemplate.COLORFUL_COLORS[2],
            ColorTemplate.LIBERTY_COLORS[0],
            ColorTemplate.LIBERTY_COLORS[1],
            ColorTemplate.LIBERTY_COLORS[2],
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_imu, container, false);

        mAccView = (LineChart) mView.findViewById(R.id.fragment_imu_linechart_acc);
        mGyrView = (LineChart) mView.findViewById(R.id.fragment_imu_linechart_gyr);
        mEncoderView = (LineChart) mView.findViewById(R.id.fragment_imu_linechart_encoder);

        initLineChart(mAccView, -2, 2);
        initLineChart(mGyrView, -2, 2);
        initLineChart(mEncoderView, 0, 360);

        return mView;
    }

    private LineDataSet createSet(String label, int index) {

        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        int color = mColors[index % mColors.length];

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(color);
        set.setCircleColor(Color.WHITE);
        set.setLineWidth(2f);
        set.setCircleRadius(4f);
        set.setFillAlpha(65);
        set.setFillColor(color);
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        set.setLabel(label);
        return set;
    }


    public void clearUi() {
        clearLineChart(mAccView);
        clearLineChart(mGyrView);
        clearLineChart(mEncoderView);
    }

    private void addDataEntry(String label, Entry e) {

        LineChart lineChart = null;
        if (label.equals(IMU.LABEL_IMU_ACC_X) || label.equals(IMU.LABEL_IMU_ACC_Y) || label.equals(IMU.LABEL_IMU_ACC_Z)) {
            lineChart = mAccView;
        }
        else if (label.equals(IMU.LABEL_IMU_GYR_X) || label.equals(IMU.LABEL_IMU_GYR_Y) || label.equals(IMU.LABEL_IMU_GYR_Z)) {
            lineChart = mGyrView;
        }
        else if (label.equals(Encoder.LABEL_ENCODER_ANGLE)) {
            lineChart = mEncoderView;
        }

        if (lineChart == null) {
            return;
        }

        LineData data = lineChart.getLineData();
        if (data == null) {
            return;
        }

        ILineDataSet set = data.getDataSetByLabel(label, true);

        if(set == null) {
            set = createSet(label, data.getDataSetCount());
            data.addDataSet(set);
        }
        set.addEntry(e);
        //data.addEntry(e, 0);
        data.notifyDataChanged();
        lineChart.notifyDataSetChanged();
        lineChart.setVisibleXRangeMaximum(10000);
        lineChart.moveViewTo(e.getX(), e.getY(), YAxis.AxisDependency.LEFT);

        // TODO Autoscale

    }

    private void initLineChart(LineChart lineChart, float min, float max) {
        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);
        lineChart.setData(data);

        XAxis xl = lineChart.getXAxis();
        //xl.setTypeface(mTfLight);
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = lineChart.getAxisLeft();
        //leftAxis.setTypeface(mTfLight);
        leftAxis.setAxisMinimum(min);
        leftAxis.setAxisMaximum(max);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void clearLineChart(LineChart lineChart) {
        LineData data = lineChart.getLineData();
        if (data != null) { data.clearValues(); }
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }
}
