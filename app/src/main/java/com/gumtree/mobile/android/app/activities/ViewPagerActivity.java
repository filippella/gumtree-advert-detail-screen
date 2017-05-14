package com.gumtree.mobile.android.app.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.transition.Explode;
import android.view.View;
import android.widget.TextView;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.adpters.AdvertPagerAdapter;
import com.gumtree.mobile.android.app.base.BaseActivity;
import com.gumtree.mobile.android.app.fragments.ZoomableImageFragment;
import com.gumtree.mobile.android.data.pojo.Constants;

import butterknife.BindView;

import static com.gumtree.mobile.android.data.pojo.Constants.IMAGES;

/**
 * Created by Filippo on 4/5/2017.
 */

public class ViewPagerActivity extends BaseActivity {

    @BindView(R.id.view_pager) protected ViewPager mPager;
    @BindView(R.id.imageCounter) protected TextView mCounter;
    private int mPosition;

    @Override
    protected int getContentView() {
        return R.layout.activity_image_preview_pager;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        mPosition = intent.getIntExtra(Constants.KEY_IMAGE_POSITION, 0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPager.setTransitionName("image--" + mPosition);
        }

        AdvertPagerAdapter adapter = new AdvertPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < IMAGES.length; i++) {
            ZoomableImageFragment fragment = ZoomableImageFragment.newInstance(IMAGES[i], i);
            adapter.addFragment(fragment);
        }
        mPager.setAdapter(adapter);
        ViewPager.SimpleOnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mCounter.setText(String.format("%d of %d", position + 1, IMAGES.length));
                Intent data = new Intent(Constants.ACTION_UPDATE_IMAGE_POSITION);
                data.putExtra(Constants.KEY_IMAGE_POSITION, position);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(data);
            }
        };
        mPager.addOnPageChangeListener(pageChangeListener);
        pageChangeListener.onPageSelected(mPager.getCurrentItem());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setExitTransition(new Explode());
        }
        mPager.setCurrentItem(mPosition);
    }

    @Override
    protected int getStatusBarColor() {
        return R.color.black;
    }

    public static void start(Activity activity, View view, int position) {
        Intent intent = new Intent(activity, ViewPagerActivity.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(activity, view, "image--"+position);
        intent.putExtra(Constants.KEY_IMAGE_POSITION, position);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            activity.startActivity(intent, options.toBundle());
        } else {
            activity.startActivity(intent);
        }
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}
