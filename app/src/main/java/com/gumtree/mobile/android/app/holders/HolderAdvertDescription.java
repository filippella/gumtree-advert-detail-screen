package com.gumtree.mobile.android.app.holders;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.base.BaseClickListener;
import com.gumtree.mobile.android.app.base.BaseViewHolder;
import com.gumtree.mobile.android.app.utilities.DrawableUtils;

import butterknife.BindView;

/**
 * Created by Filippo on 4/5/2017.
 */

public class HolderAdvertDescription extends BaseViewHolder<String[], BaseClickListener> {

    @BindView(R.id.advertDetailsContainer) protected LinearLayout mAdvertDetailsContainer;
    @BindView(R.id.detailIcon) protected ImageView mDetailIcon;

    public HolderAdvertDescription(View itemView, @DrawableRes int drawableRes) {
        super(itemView);
        Context context = itemView.getContext();
        Drawable drawable = ContextCompat.getDrawable(context, drawableRes);
        mDetailIcon.setImageDrawable(drawable);
        DrawableUtils.tintDrawable(context, drawable, R.color.lightGray);
    }

    @Override
    public void bind(String[] model) {
        if (model != null) {
            mAdvertDetailsContainer.removeAllViews();
            Context context = mAdvertDetailsContainer.getContext();
            for (String description : model) {
                TextView child = new TextView(context);
                child.setText(description);
                child.setTextSize(16f);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.bottomMargin = 35;
                mAdvertDetailsContainer.addView(child, params);
            }
        }
    }
}

