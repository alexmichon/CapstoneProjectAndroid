package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_result;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseResult;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.metric.MetricResult;

/**
 * Created by Alex on 17/12/2017.
 */

public class ExerciseResultAdapter extends RecyclerView.Adapter<ExerciseResultAdapter.ViewHolder> {

    private final ExerciseResult mExerciseResult;

    public ExerciseResultAdapter(ExerciseResult exerciseResult) {
        mExerciseResult = exerciseResult;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return mExerciseResult.getSize();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.metric_summary_label)
        TextView mLabelView;

        @BindView(R.id.metric_summary_actual)
        TextView mActualView;

        @BindView(R.id.metric_summary_expected)
        TextView mExpectedView;

        @BindView(R.id.metric_summary_comment)
        TextView mCommentView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(MetricResult metricResult) {

        }
    }
}
