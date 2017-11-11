package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.berkeley.capstoneproject.capstoneprojectandroid.R;

/**
 * Created by Alex on 10/11/2017.
 */

public class DrawerAdapter extends ArrayAdapter<DrawerItem> {
    private static final String TAG = DrawerAdapter.class.getSimpleName();

    private static final int DRAWER_HEADER = 0;
    private static final int DRAWER_LIST = 1;

    private Context mContext;
    private List<DrawerItem> mDrawerItems;
    private String mName = "Feather 52";
    private DrawerAdapter.OnItemSelectedListener mListener;

    public class ViewHolder extends  RecyclerView.ViewHolder {

        int mHolderId;

        TextView mNameView;

        ImageView mIconView;
        TextView mTitleView;

        public ViewHolder(View itemView, int viewType) {
            super(itemView);

            switch (viewType) {
                case DRAWER_HEADER:
                    mNameView = (TextView) itemView.findViewById(R.id.drawer_name);
                    mHolderId = DRAWER_HEADER;
                    break;
                case DRAWER_LIST:
                    mIconView = (ImageView) itemView.findViewById(R.id.item_drawer_row_imageview_icon);
                    mTitleView = (TextView) itemView.findViewById(R.id.item_drawer_row_textview_title);
                    mHolderId = DRAWER_LIST;
                    break;
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(view, getAdapterPosition());
                }
            });
        }
    }

    public DrawerAdapter(Context context, @LayoutRes int layoutResId, DrawerItem[] drawerItems) {
        super(context, layoutResId, drawerItems);
    }

    @Override
    public int getCount() {
        return mDrawerItems.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return isPositionHeader(position)? DRAWER_HEADER : DRAWER_LIST;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public void setOnItemSelectedListener(DrawerAdapter.OnItemSelectedListener listener) {
        mListener = listener;
    }

    public interface OnItemSelectedListener {
        public void onItemSelected(View v, int position);
    }
}
