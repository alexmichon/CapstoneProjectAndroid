package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.training.exercise_type.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExerciseTypesAdapter extends RecyclerView.Adapter<ExerciseTypesAdapter.Holder> {

    private final List<ExerciseType> mExerciseTypes;
    private ExerciseTypesAdapterListener mListener;

    public ExerciseTypesAdapter(List<ExerciseType> exerciseTypes) {
        mExerciseTypes = exerciseTypes;
    }

    public ExerciseTypesAdapter(List<ExerciseType> exerciseTypes, ExerciseTypesAdapterListener listener) {
        mExerciseTypes = exerciseTypes;
        mListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_exercise_type, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ExerciseType exerciseType = mExerciseTypes.get(position);
        holder.bind(exerciseType);
    }

    @Override
    public int getItemCount() {
        return mExerciseTypes.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.card_exercise_type_name)
        TextView mTextName;

        @BindView(R.id.card_exercise_type_description)
        TextView mTextDescription;

        @BindView(R.id.card_exercise_type_more_button)
        Button mMoreButton;

        @BindView(R.id.card_exercise_type_start_button)
        Button mStartButton;

        ExerciseType mExerciseType;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(final ExerciseType exerciseType) {
            mExerciseType = exerciseType;

            mTextName.setText(exerciseType.getName());
            mTextDescription.setText(exerciseType.getDescription());

            mMoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onMoreClick(exerciseType);
                    }
                }
            });

            mStartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListener != null) {
                        mListener.onStartClick(exerciseType);
                    }
                }
            });
        }
    }

    public interface ExerciseTypesAdapterListener {
        void onMoreClick(ExerciseType exerciseType);
        void onStartClick(ExerciseType exerciseType);
    }
}
