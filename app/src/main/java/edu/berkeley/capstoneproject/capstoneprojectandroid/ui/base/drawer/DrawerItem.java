package edu.berkeley.capstoneproject.capstoneprojectandroid.ui.base.drawer;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by Alex on 10/11/2017.
 */

public class DrawerItem {

    private String mTitle;
    private int mIcon;

    public DrawerItem(String title, @DrawableRes int icon) {
        mTitle = title;
        mIcon = icon;
    }
}
