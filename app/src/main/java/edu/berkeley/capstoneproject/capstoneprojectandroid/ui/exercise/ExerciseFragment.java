package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.bluetooth.model.Measurement;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.SensorManager;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.BaseFragment;
import timber.log.Timber;

/**
 * Created by Alex on 18/11/2017.
 */

public class ExerciseFragment extends BaseFragment<ExerciseContract.View, ExerciseContract.Presenter<ExerciseContract.View, ExerciseContract.Interactor>> implements ExerciseContract.View {

    private static final String TAG = ExerciseFragment.class.getSimpleName();

    private static final String TITLE = "Exercise";

    public static final String EXTRA_EXERCISE_TYPE = "ExerciseType";


    private NumberFormat mTimerFormatter;

    @BindView(R.id.exercise_start_button)
    Button mStartButton;

    @BindView(R.id.exercise_countdown)
    TextView mCountdownView;
    @BindView(R.id.exercise_timer)
    TextView mTimerView;
    @BindView(R.id.exercise_timer_max)
    TextView mTimerMaxView;

    @BindView(R.id.exercise_layout)
    LinearLayout mExerciseLayout;

    @BindView(R.id.exercise_linechart_acc)
    LineChart mAccView;
    @BindView(R.id.exercise_linechart_gyr)
    LineChart mGyrView;
    @BindView(R.id.exercise_linechart_encoder)
    PieChart mEncoderView;

    private int[] mColors = new int[] {
            ColorTemplate.COLORFUL_COLORS[0],
            ColorTemplate.COLORFUL_COLORS[1],
            ColorTemplate.COLORFUL_COLORS[2],
            ColorTemplate.LIBERTY_COLORS[0],
            ColorTemplate.LIBERTY_COLORS[1],
            ColorTemplate.LIBERTY_COLORS[2],
    };

    private ExerciseType mExerciseType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresenter().onCreate();

        mTimerFormatter = new DecimalFormat("00.00");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_exercise, container, false);

        Bundle data = getArguments();
        mExerciseType = (ExerciseType) data.getParcelable(EXTRA_EXERCISE_TYPE);
        if (mExerciseType == null) {
            Timber.e("Exercise type can't be null");
            return null;
        }

        ButterKnife.bind(this, view);

        initLineChart(mAccView, -2, 2);
        initLineChart(mGyrView, -2, 2);
        initPieChart(mEncoderView);

        mStartButton.setVisibility(View.GONE);
        mExerciseLayout.setVisibility(View.GONE);
        mCountdownView.setVisibility(View.GONE);

        return view;
    }

    @Override
    public ExerciseContract.Presenter<ExerciseContract.View, ExerciseContract.Interactor> createPresenter() {
        return getActivityComponent().exercisePresenter();
    }

    @Override
    public String getTitle() {
        return TITLE;
    }


    @Override
    public ExerciseType getExerciseType() {
        return mExerciseType;
    }

    @Override
    public void onExerciseCreated(Exercise exercise) {
        setTimerMax(exercise.getDuration());
    }

    @Override
    public void onExerciseReady(Exercise exercise) {
        hideLoading();
        showStartButton();
    }


    @Override
    public void onExerciseStopped(Exercise exercise) {

    }

    @Override
    public void onExerciseError(Throwable throwable) {
        hideLoading();
        showError(throwable);
    }

    @Override
    public void onExerciseFinished() {
        onTimerFinished();
    }


    @OnClick(R.id.exercise_start_button)
    public void onStartClick(View v) {
        getPresenter().onStartClick();
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
                setPieMeasurement(mEncoderView, measurement);
                return;
        }

        if (chart != null) {
            addMeasurement(chart, measurement);
        }
    }

    @Override
    public void onCreatingExercise() {
        showLoading("Wait...", false);
    }

    @Override
    public void onStartingExercise() {

    }

    @Override
    public void onStartRecording() {
        showExerciseLayout();
        onTimerStart();
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
        Timber.d("Adding measurement: %d, %d, %d, %.2f", measurement.getMetric().getSensor().getId(), measurement.getMetric().getId(), measurement.getTimestamp(), measurement.getValue());
        data.notifyDataChanged();
        chart.notifyDataSetChanged();
        chart.setVisibleXRangeMaximum(1000);
        chart.moveViewToX(e.getX());
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
        clearPieChart(mEncoderView);
    }

    private void initPieChart(PieChart pieChart) {
        pieChart.setUsePercentValues(false);
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
        /*xl.setGranularity(100f);
        xl.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });*/
        xl.setEnabled(true);

        YAxis leftAxis = lineChart.getAxisLeft();
        //leftAxis.setTypeface(mTfLight);
        leftAxis.setAxisMinimum(min);
        leftAxis.setAxisMaximum(max);
        /*leftAxis.setGranularity(30f);
        leftAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf(value);
            }
        });*/
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void setPieMeasurement(PieChart pieChart, Measurement measurement) {
        Timber.d("Adding measurement: %d, %d, %d, %.2f", measurement.getMetric().getSensor().getId(), measurement.getMetric().getId(), measurement.getTimestamp(), measurement.getValue());

        float angle = (float) (measurement.getValue() * 180 / Math.PI) + 180f;

        List<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(angle));

        Metric metric = measurement.getMetric();
        IPieDataSet set = new PieDataSet(pieEntries, metric.getName());

        PieData data = new PieData(set);
        pieChart.setData(data);

        pieChart.setMaxAngle(angle);

        data.notifyDataChanged();
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
    }

    private PieDataSet createPieSet(String label, int index) {
        PieDataSet set = new PieDataSet(null, "Dynamic Data");
        int color = mColors[index % mColors.length];

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(color);
        set.setValueTextColor(Color.BLACK);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        set.setLabel(label);
        return set;
    }

    private void clearLineChart(LineChart lineChart) {
        LineData data = lineChart.getLineData();
        if (data != null) { data.clearValues(); }
        lineChart.notifyDataSetChanged();
        lineChart.invalidate();
    }

    private void clearPieChart(PieChart pieChart) {
        PieData data = pieChart.getData();
        if (data != null) { data.clearValues(); }
        pieChart.notifyDataSetChanged();
        pieChart.invalidate();
    }



    @Override
    public void onCountdownStart() {
        showCountdown();
        mCountdownView.setText("Ready ?");
    }

    @Override
    public void onCountdownUpdate(int count) {
        mCountdownView.setText(String.valueOf(count));
    }

    @Override
    public void onCountdownFinished() {
        getPresenter().onStartRecording();
    }




    @Override
    public void setTimerMax(float timerMax) {
        mTimerMaxView.setText(mTimerFormatter.format(timerMax));
    }

    @Override
    public void onTimerStart() {
        onTimerUpdate(0);
    }

    @Override
    public void onTimerUpdate(float time) {
        mTimerView.setText(mTimerFormatter.format(time));
    }

    @Override
    public void onTimerFinished() {
        mTimerView.setText(mTimerMaxView.getText());
    }





    private void showStartButton() {
        mStartButton.setVisibility(View.VISIBLE);
        mExerciseLayout.setVisibility(View.GONE);
        mCountdownView.setVisibility(View.GONE);
    }

    private void showCountdown() {
        mStartButton.setVisibility(View.GONE);
        mExerciseLayout.setVisibility(View.GONE);
        mCountdownView.setVisibility(View.VISIBLE);
    }

    private void showExerciseLayout() {
        mStartButton.setVisibility(View.GONE);
        mExerciseLayout.setVisibility(View.VISIBLE);
        mCountdownView.setVisibility(View.GONE);
    }
}
