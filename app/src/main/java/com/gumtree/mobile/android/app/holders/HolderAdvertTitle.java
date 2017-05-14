package com.gumtree.mobile.android.app.holders;

import android.view.View;
import android.widget.TextView;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.base.BaseClickListener;
import com.gumtree.mobile.android.app.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by Filippo on 4/5/2017.
 */

public class HolderAdvertTitle extends BaseViewHolder<String, BaseClickListener> {

    @BindView(R.id.advertTitle) protected TextView mAdvertTitle;

    public HolderAdvertTitle(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(String model) {
        mAdvertTitle.setText(model);
    }
}
