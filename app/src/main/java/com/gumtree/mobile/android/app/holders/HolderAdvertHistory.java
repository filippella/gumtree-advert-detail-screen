package com.gumtree.mobile.android.app.holders;

import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.base.BaseViewHolder;
import com.gumtree.mobile.android.app.adpters.AdvertDetailAdapter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Filippo on 4/5/2017.
 */

public class HolderAdvertHistory extends BaseViewHolder<String, AdvertDetailAdapter.OnAdvertHistoryClickListener> {

    @BindView(R.id.advertHistory) protected AppCompatButton mAdvertHistory;

    public HolderAdvertHistory(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(String model) {
        mAdvertHistory.setText(model);
    }

    @OnClick(R.id.advertHistory)
    void onHistoryButtonClick() {
        if (mListener != null) {
            mListener.onClicked();
        }
    }
}
