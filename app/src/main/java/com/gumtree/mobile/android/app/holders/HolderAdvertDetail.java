package com.gumtree.mobile.android.app.holders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.base.BaseClickListener;
import com.gumtree.mobile.android.app.base.BaseViewHolder;
import com.gumtree.mobile.android.app.utilities.DrawableUtils;

import java.util.Map;

import butterknife.BindView;

/**
 * Created by Filippo on 4/5/2017.
 */

public class HolderAdvertDetail extends BaseViewHolder<Map<String, String>, BaseClickListener> {

    @BindView(R.id.advertDetailsContainer) protected LinearLayout mAdvertDetailsContainer;
    @BindView(R.id.detailIcon) protected ImageView mDetailIcon;

    public HolderAdvertDetail(View itemView, @DrawableRes int drawableRes) {
        super(itemView);
        Context context = itemView.getContext();
        Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
        mDetailIcon.setImageDrawable(drawable);
        DrawableUtils.tintDrawable(context, drawable, R.color.lightGray);
    }

    @Override
    public void bind(Map<String, String> model) {
        if (model != null) {
            mAdvertDetailsContainer.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(mAdvertDetailsContainer.getContext());
            for (Map.Entry<String, String> entry : model.entrySet()) {
                View child = inflater.inflate(R.layout.item_advert_single_detail_layout, mAdvertDetailsContainer, false);
                TextView advertDetailInfo = (TextView) child.findViewById(R.id.advertDetailInfo);
                TextView advertDetailValue = (TextView) child.findViewById(R.id.advertDetailValue);
                advertDetailInfo.setText(entry.getKey());
                advertDetailValue.setText(entry.getValue());
                mAdvertDetailsContainer.addView(child);
            }
        }
    }
}