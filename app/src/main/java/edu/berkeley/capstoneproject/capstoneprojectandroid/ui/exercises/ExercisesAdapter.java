package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercises;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.models.exercises.Exercise;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExercisesAdapter extends ArrayAdapter<Exercise> {

    private static final String TAG = ExercisesAdapter.class.getSimpleName();

    private LayoutInflater mInflater;

    public ExercisesAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);
    }

    static class Holder {
        @BindView(R.id.row_exercise_name) TextView mTextName;
        @BindView(R.id.row_exercise_description) TextView mTextAddress;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_exercise, null);

            holder = new Holder(convertView);
            holder.mTextName = (TextView) convertView.findViewById(R.id.text_bluetooth_name);
            holder.mTextAddress = (TextView) convertView.findViewById(R.id.text_bluetooth_address);

            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }

        Exercise exercise = getItem(position);
        if (exercise != null) {
            holder.mTextName.setText(exercise.getName());
            holder.mTextAddress.setText(exercise.getDescription());
        }

        return convertView;
    }
}
