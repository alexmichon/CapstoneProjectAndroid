package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.exercise_type;

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
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.ExerciseType;

/**
 * Created by Alex on 08/11/2017.
 */

public class ExerciseTypesAdapter extends ArrayAdapter<ExerciseType> {

    private static final String TAG = ExerciseTypesAdapter.class.getSimpleName();

    private LayoutInflater mInflater;

    public ExerciseTypesAdapter(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);
    }

    static class Holder {
        @BindView(R.id.row_exercise_name) TextView mTextName;
        @BindView(R.id.row_exercise_description) TextView mTextDescription;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }

        public void setName(String name) {
            mTextName.setText(name);
        }

        public void setDescription(String description) {
            mTextDescription.setText(description);
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Holder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_exercise, parent);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (Holder) convertView.getTag();
        }

        ExerciseType exerciseType = getItem(position);
        if (exerciseType != null) {
            holder.setName(exerciseType.getName());
            holder.setDescription(exerciseType.getDescription());
        }

        return convertView;
    }
}
