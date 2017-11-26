package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.di.component.ActivityComponent;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import timber.log.Timber;

/**
 * Created by Alex on 18/11/2017.
 */

public class ExerciseFragment extends BaseFragment implements ExerciseContract.View {

    private static final String TAG = ExerciseFragment.class.getSimpleName();

    private static final String TITLE = "Exercise";

    public static final String EXTRA_EXERCISE_TYPE = "ExerciseType";

    @Inject
    ExerciseContract.Presenter<ExerciseContract.View, ExerciseContract.Interactor> mPresenter;


    @BindView(R.id.exercise_linechart_acc)
    LineChart mAccView;
    @BindView(R.id.exercise_linechart_gyr)
    LineChart mGyrView;
    @BindView(R.id.exercise_linechart_encoder)
    LineChart mEncoderView;

    private int[] mColors = new int[] {
            ColorTemplate.COLORFUL_COLORS[0],
            ColorTemplate.COLORFUL_COLORS[1],
            ColorTemplate.COLORFUL_COLORS[2],
            ColorTemplate.LIBERTY_COLORS[0],
            ColorTemplate.LIBERTY_COLORS[1],
            ColorTemplate.LIBERTY_COLORS[2],
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercise, container, false);

        Bundle data = getArguments();
        ExerciseType exerciseType = (ExerciseType) data.getParcelable(EXTRA_EXERCISE_TYPE);
        if (exerciseType == null) {
            Timber.e("Exercise type can't be null");
            return null;
        }

        ActivityComponent component = getActivityComponent();
        if (component != null) {
            component.inject(this);
            ButterKnife.bind(this, view);
            mPresenter.onAttach(this, exerciseType);
        }

        initLineChart(mAccView, -2, 2);
        initLineChart(mGyrView, -2, 2);
        initLineChart(mEncoderView, 0, 360);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onDestroyView() {
        mPresenter.onDetach();
        super.onDestroyView();
    }

    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_exercise, menu);
        menu.findItem(R.id.exercise_menu_start).setVisible(!mPresenter.isStarted());
        menu.findItem(R.id.exercise_menu_stop).setVisible(mPresenter.isStarted());
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exercise_menu_start:
                mPresenter.onStartClick();
                return true;
            case R.id.exercise_menu_stop:
                mPresenter.onStopClick();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onExerciseCreated(Exercise exercise) {

    }

    @Override
    public void onExerciseStarted(Exercise exercise) {
        getActivity().invalidateOptionsMenu();
        hideLoading();
        showMessage("Let's go !");
    }

    @Override
    public void onExerciseStopped(Exercise exercise) {
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onExerciseError(Throwable throwable) {
        hideLoading();
        showError(throwable);
    }

    @Override
    public void addMeasurement(Measurement measurement) {
        LineChart chart = null;

        switch (measurement.getMetric().getSensor().getId()) {
            case SensorManager.ID_ACCELEROMETER:
                chart = mAccView;
                break;
            case SensorManager.ID_GYROSCOPE:
                chart = mGyrView;
                break;
            case SensorManager.ID_ENCODER:
                chart = mEncoderView;
                break;
        }

        addMeasurement(chart, measurement);
    }

    @Override
    public void onCreatingExercise() {
        showLoading("Wait...");
    }

    @Override
    public void onStartingExercise() {

    }

    private void addMeasurement(LineChart chart, Measurement measurement) {
        LineData data = chart.getLineData();
        if (data == null) {
            return;
        }

        Metric metric = measurement.getMetric();
        ILineDataSet set = data.getDataSetByLabel(metric.getName(), true);

        if(set == null) {
            set = createSet(metric.getName(), data.getDataSetCount());
            data.addDataSet(set);
        }
        Entry e = new Entry(measurement.getTimestamp(), measurement.getValue());
        set.addEntry(e);
        data.notifyDataChanged();
        chart.notifyDataSetChanged();
        chart.setVisibleXRangeMaximum(10000);
        chart.moveViewTo(e.getX(), e.getY(), YAxis.AxisDependency.LEFT);
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
