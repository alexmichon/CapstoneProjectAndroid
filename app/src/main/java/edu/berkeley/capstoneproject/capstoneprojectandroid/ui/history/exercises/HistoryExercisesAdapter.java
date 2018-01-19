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

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.data.model.exercise.Exercise;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryExercisesAdapter extends ArrayAdapter<Exercise> {

    private LayoutInflater mInflater;
    private Locale mLocale;

    public HistoryExercisesAdapter(@NonNull Context context, @LayoutRes int resource, Locale locale) {
        super(context, resource);
        mInflater = LayoutInflater.from(context);
        mLocale = locale;
    }

    class Holder {
        @BindView(R.id.row_exercise_name)
        TextView mTextName;
        @BindView(R.id.row_exercise_date) TextView mTextDate;

        Holder(View view) {
            ButterKnife.bind(this, view);
        }

        void setName(String name) {
            mTextName.setText(name);
        }

        void setDate(Date date) {
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, mLocale);
            mTextDate.setText(dateFormat.format(date));
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        HistoryExercisesAdapter.Holder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.row_exercise, parent, false);
            holder = new HistoryExercisesAdapter.Holder(convertView);
            convertView.setTag(holder);
        }
        else {
            holder = (HistoryExercisesAdapter.Holder) convertView.getTag();
        }

        Exercise exercise = getItem(position);
        if (exercise != null) {
            holder.setName(exercise.getName());
            holder.setDate(exercise.getStartDate());
        }

        return convertView;
    }

}
