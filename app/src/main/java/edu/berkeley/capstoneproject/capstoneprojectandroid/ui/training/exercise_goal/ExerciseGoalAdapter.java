package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_goal;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.Metric;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricGoal;
import timber.log.Timber;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalAdapter extends RecyclerView.Adapter<ExerciseGoalAdapter.ViewHolder> {

    private final List<MetricGoal> mMetricGoals = new ArrayList<>();

    private boolean mEditable = false;

    public ExerciseGoalAdapter(List<MetricGoal> metricGoals) {
        try {
            for (MetricGoal m: metricGoals) {
                mMetricGoals.add(m.clone());
            }
        } catch (CloneNotSupportedException e) {
            Timber.e(e);
        }
    }

    public void setEditable(boolean editable) {
        mEditable = editable;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_metric_goal, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.setGoalListener(new GoalTextChangedListener(i));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        MetricGoal metricGoal = mMetricGoals.get(i);
        viewHolder.bind(metricGoal);
    }

    @Override
    public int getItemCount() {
        return mMetricGoals.size();
    }

    public List<MetricGoal> getMetricGoals() {
        return mMetricGoals;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.metric_goal_label)
        TextView mLabelView;

        @BindView(R.id.metric_goal_value)
        EditText mGoalView;

        private MetricGoal mMetricGoal;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setGoalListener(TextWatcher textWatcher) {
            mGoalView.addTextChangedListener(textWatcher);
        }

        public void bind(MetricGoal metricGoal) {
            mMetricGoal = metricGoal;

            Metric metric = metricGoal.getMetric();
            float goal = metricGoal.getGoal();

            mLabelView.setText(metric.getName());
            mGoalView.setText(String.valueOf(goal));
            mGoalView.setEnabled(mEditable);
        }
    }

    public class GoalTextChangedListener implements TextWatcher {
        private final int mPosition;

        public GoalTextChangedListener(int position) {
            mPosition = position;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            mMetricGoals.get(mPosition).setGoal(Float.valueOf(editable.toString()));
        }
    }
}
