package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.views.metric_result;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricResult;

/**
 * Created by Alex on 17/01/2018.
 */

public class MetricResultChart extends HorizontalBarChart {

    public MetricResultChart(Context context) {
        super(context);
        initView();
    }

    public MetricResultChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MetricResultChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setDrawBarShadow(false);

        setDrawValueAboveBar(true);

        getDescription().setEnabled(false);
        getLegend().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        setMaxVisibleValueCount(1);

        // scaling can now only be done on x- and y-axis separately
        setPinchZoom(false);

        // draw shadows for each bar that show the maximum value
        // mChart.setDrawBarShadow(true);

        setDrawGridBackground(false);

        XAxis xl = getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setDrawAxisLine(true);
        xl.setDrawGridLines(false);
        xl.setDrawLimitLinesBehindData(false);
        xl.setEnabled(false);

        YAxis yl = getAxisLeft();
        yl.setDrawAxisLine(false);
        yl.setDrawGridLines(false);
//        yl.setInverted(true);
        yl.setDrawLimitLinesBehindData(false);
        yl.setEnabled(false);

        YAxis yr = getAxisRight();
        yr.setDrawAxisLine(false);
        yr.setDrawGridLines(false);
        yr.setDrawLimitLinesBehindData(false);

        setFitBars(true);
    }

    public void setMetricResult(MetricResult metricResult) {
        getAxisLeft().setAxisMinimum(metricResult.getMetricGoal().getMetricMin());
        getAxisLeft().setAxisMaximum(metricResult.getMetricGoal().getMetricMax());

        getAxisRight().setAxisMinimum(metricResult.getMetricGoal().getMetricMin());
        getAxisRight().setAxisMaximum(metricResult.getMetricGoal().getMetricMax());

        LimitLine ll = new LimitLine(metricResult.getExpected());
        ll.setLineColor(Color.BLACK);
        ll.setLineWidth(5f);
        ll.setLabel(String.valueOf(metricResult.getExpected()));
        ll.setTextSize(15f);
        ll.setTextColor(Color.BLACK);

        if (metricResult.getExpected() > metricResult.getActual()) {
            ll.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        }
        else {
            ll.setLabelPosition(LimitLine.LimitLabelPosition.LEFT_TOP);
        }

        getAxisRight().removeAllLimitLines();
        getAxisRight().addLimitLine(ll);

        MetricResultDataSet dataSet = new MetricResultDataSet(metricResult);

        BarData barData = new BarData(dataSet);
        barData.setBarWidth(5f);

        setData(barData);
    }
}
