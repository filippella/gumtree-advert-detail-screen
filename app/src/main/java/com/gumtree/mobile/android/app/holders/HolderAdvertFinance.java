package com.gumtree.mobile.android.app.holders;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.base.BaseClickListener;
import com.gumtree.mobile.android.app.base.BaseViewHolder;
import com.gumtree.mobile.android.data.pojo.Finance;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Filippo on 4/5/2017.
 */

public class HolderAdvertFinance extends BaseViewHolder<Finance, BaseClickListener> {

    @BindView(R.id.advertFinanceDetailContainer) protected LinearLayout mFinanceDetailContainer;
    @BindView(R.id.advertFinanceTxt) protected TextView mFinanceTxt;
    private int originalHeight;
    private boolean mIsViewExpanded;

    public HolderAdvertFinance(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Finance model) {
        mFinanceTxt.setText(model.title);
    }

    @OnClick(R.id.advertFinanceInfo)
    void onAdvertInfoClick(final View view) {

        //Code below has been taken from https://gist.github.com/ZkHaider/9bf0e1d7b8a2736fd676

        if (originalHeight == 0) {
            originalHeight = view.getHeight();
        }

        ValueAnimator valueAnimator;
        if (!mIsViewExpanded) {
            mFinanceDetailContainer.setVisibility(View.VISIBLE);
            mIsViewExpanded = true;
            valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight * 6.0)); // These values in this method can be changed to expand however much you like
        } else {
            mIsViewExpanded = false;
            valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 6.0), originalHeight);

            Animation alphaAnimation = new AlphaAnimation(1.00f, 0.00f); // Fade out
            alphaAnimation.setDuration(200);
            // Set alphaAnimation listener to the animation and configure onAnimationEnd
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    mFinanceDetailContainer.setVisibility(View.INVISIBLE);
                    mFinanceDetailContainer.setEnabled(false);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            // Set the animation on the custom view
            mFinanceDetailContainer.startAnimation(alphaAnimation);
        }
        valueAnimator.setDuration(200);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                view.getLayoutParams().height = value.intValue();
                view.requestLayout();
            }
        });
        valueAnimator.start();
    }
}
