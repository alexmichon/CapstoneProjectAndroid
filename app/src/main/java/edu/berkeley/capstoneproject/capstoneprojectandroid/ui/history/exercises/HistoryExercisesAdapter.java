package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryExercisesAdapter extends RecyclerView.Adapter<HistoryExercisesAdapter.Holder> {

    private static final Locale LOCALE = Locale.getDefault();

    private final List<Exercise> mExercises;
    private HistoryExercisesAdapterListener mListener;

    public HistoryExercisesAdapter(List<Exercise> exercises) {
        mExercises = exercises;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_exercise, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.bind(mExercises.get(position));
    }

    @Override
    public int getItemCount() {
        return mExercises.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private Exercise mExercise;

        @BindView(R.id.item_history_exercise_name)
        TextView mTextName;

        @BindView(R.id.item_history_exercise_date)
        TextView mTextDate;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onHistoryExerciseSelect(mExercise);
                    }
                }
            });
        }

        public void bind(Exercise exercise) {
            mExercise = exercise;

            mTextName.setText(exercise.getExerciseTypeName());

            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, LOCALE);
            mTextDate.setText(dateFormat.format(exercise.getStartDate()));
        }
    }


    public void setListener(HistoryExercisesAdapterListener listener) {
        mListener = listener;
    }

    public interface HistoryExercisesAdapterListener {
        void onHistoryExerciseSelect(Exercise exercise);
    }

}
