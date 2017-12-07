package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history.exercises;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;

/**
 * Created by Alex on 06/12/2017.
 */

public class ExercisesAdapter extends ArrayAdapter<Exercise> {

    private LayoutInflater mInflater;

    public ExercisesAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);
    }

    static class Holder {
        @BindView(R.id.row_exercise_name)
        TextView mTextName;
        @BindView(R.id.row_exercise_date) TextView mTextDate;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setName(String name) {
            mTextName.setText(name);
        }

        public void setDate(Date date) {
            mTextDate.setText(date.toString());
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ExercisesAdapter.Holder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_exercise, parent, false);
            holder = new ExercisesAdapter.Holder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (ExercisesAdapter.Holder) convertView.getTag();
        }

        Exercise exercise = getItem(position);
        if (exercise != null) {
            holder.setName(exercise.getType().getName());
            holder.setDate(exercise.getStartDate());
        }

        return convertView;
    }

}
