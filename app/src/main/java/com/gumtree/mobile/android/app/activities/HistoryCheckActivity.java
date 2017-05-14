package com.gumtree.mobile.android.app.activities;

import android.content.Intent;
import android.os.Bundle;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.base.BaseActivity;

/**
 * Created by Filippo on 4/6/2017.
 */

public class HistoryCheckActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_additional_feature;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        showBack();
        setTitle("Car History Check");
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.black;
    }
}
