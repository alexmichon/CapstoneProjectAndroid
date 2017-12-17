package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_goal;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseGoal;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.sensor.Metric;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseGoalAdapter extends RecyclerView.Adapter<ExerciseGoalAdapter.ViewHolder> {

    private final ExerciseGoal mExerciseGoal;

    public ExerciseGoalAdapter(ExerciseGoal exerciseGoal) {
        mExerciseGoal = exerciseGoal;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_metric_goal, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Metric metric = mExerciseGoal.getMetric(i);
        float goal = mExerciseGoal.getGoal(metric);

        viewHolder.bind(metric, goal);
    }

    @Override
    public int getItemCount() {
        return mExerciseGoal.getGoals().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.metric_goal_label)
        TextView mLabelView;

        @BindView(R.id.metric_goal_value)
        EditText mValueView;

        private Metric mMetric;
        private float mGoal;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Metric metric, float goal) {
            mMetric = metric;
            mGoal = goal;

            mLabelView.setText(metric.getName());
            mValueView.setText(String.valueOf(goal));
        }
    }
}
