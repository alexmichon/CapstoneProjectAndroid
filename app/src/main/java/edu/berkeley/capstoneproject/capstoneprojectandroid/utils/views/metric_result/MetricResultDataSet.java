package edu.berkeley.capstoneproject.capstoneprojectandroid.utils.views.metric_result;

import android.graphics.Color;

import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;
import java.util.Collections;

import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricResult;

/**
 * Created by Alex on 17/01/2018.
 */

public class MetricResultDataSet extends BarDataSet {

    private static final int COLOR_SUCCESS_BAR = Color.GREEN;
    private static final int COLOR_FAILURE_BAR = Color.RED;

    private static final int COLOR_SUCCESS_TEXT = Color.rgb(0, 150, 50);
    private static final int COLOR_FAILURE_TEXT = Color.rgb(200, 50, 0);

    public MetricResultDataSet(MetricResult metricResult) {
        super(Collections.singletonList(new BarEntry(0, metricResult.getActual())), metricResult.getMetricName());

        int colorBar;
        int colorText;
        if (metricResult.getResult()) {
            colorBar = COLOR_SUCCESS_BAR;
            colorText = COLOR_SUCCESS_TEXT;
        }
        else {
            colorBar = COLOR_FAILURE_BAR;
            colorText = COLOR_FAILURE_TEXT;
        }

        setColor(colorBar);
        setDrawValues(true);
        setDrawIcons(false);
        setValueTextSize(10f);
        setValueTextColor(colorText);
        setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return new DecimalFormat("###,##0.0").format(value);
            }
        });
    }
}
