package com.gumtree.mobile.android.app.holders;

import android.view.View;
import android.widget.TextView;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.base.BaseClickListener;
import com.gumtree.mobile.android.app.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Filippo on 4/5/2017.
 */

public class HolderAdvertProfileLink extends BaseViewHolder<String, BaseClickListener> {

    @BindView(R.id.advertProfileTxt) protected TextView mAdvertProfileTxt;

    public HolderAdvertProfileLink(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(String model) {
        mAdvertProfileTxt.setText(model);
    }

    @OnClick(R.id.advertiserInfo)
    void onAdvertiserInfoClick(View view) {
        showToast(view.getContext(), "Clicked for more Advertiser Info");
    }
}