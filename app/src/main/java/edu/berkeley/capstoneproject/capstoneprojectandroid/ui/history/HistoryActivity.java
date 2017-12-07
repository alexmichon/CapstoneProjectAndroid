package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.history;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import edu.berkeley.capstoneproject.capstoneprojectandroid.R;
import edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer.DrawerActivity;

/**
 * Created by Alex on 06/12/2017.
 */

public class HistoryActivity extends DrawerActivity<HistoryContract.View, HistoryContract.Presenter<HistoryContract.View, HistoryContract.Interactor>> implements HistoryContract.View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        setUnbinder(ButterKnife.bind(this));
    }

    @NonNull
    @Override
    public HistoryContract.Presenter<HistoryContract.View, HistoryContract.Interactor> createPresenter() {
        return getActivityComponent().historyPresenter();
    }
}
