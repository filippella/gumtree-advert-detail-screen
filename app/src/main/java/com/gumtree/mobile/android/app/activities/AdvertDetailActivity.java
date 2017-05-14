package com.gumtree.mobile.android.app.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.gumtree.mobile.android.app.adpters.AdvertPagerAdapter;
import com.gumtree.mobile.android.app.delegates.AdvertActionDelegate;
import com.gumtree.mobile.android.app.fragments.ImageFragment;
import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.adpters.AdvertDetailAdapter;
import com.gumtree.mobile.android.app.base.BaseActivity;
import com.gumtree.mobile.android.app.di.components.DaggerAdvertDetailComponent;
import com.gumtree.mobile.android.app.di.modules.AdvertDetailModule;
import com.gumtree.mobile.android.data.pojo.AdvertInfo;
import com.gumtree.mobile.android.data.pojo.Constants;
import com.gumtree.mobile.android.data.presentation.AdvertDetailView;
import com.gumtree.mobile.android.presenter.advert.AdvertDetailPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static com.gumtree.mobile.android.data.pojo.Constants.IMAGES;

/**
 * Created by Filippo on 4/5/2017.
 */

public class AdvertDetailActivity extends BaseActivity implements AdvertDetailView {

    private final static String TAG = AdvertDetailActivity.class.getSimpleName();

    @BindView(R.id.recyclerView) protected RecyclerView mAdvertDetailList;
    @BindView(R.id.pager_advert) protected ViewPager mPager;
    @BindView(R.id.appbar) protected AppBarLayout mAppBar;
    @BindView(R.id.collapsingToolbarLayout) protected CollapsingToolbarLayout mCollapsingToolbarLayout;

    @Inject protected AdvertDetailPresenter mPresenter;
    @Inject protected AdvertActionDelegate mActionDelegate;

    private AdvertDetailAdapter mAdvertDetailAdapter;
    private BroadcastReceiver mBroadcastReceiver;

    @Override
    protected void resolveDependency() {
        DaggerAdvertDetailComponent.builder()
                .advertDetailModule(new AdvertDetailModule(AdvertDetailActivity.this))
                .build().inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_detail_screen;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_advert_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionFavourite:
                boolean checked = item.isChecked();
                item.setIcon(mPresenter.onGetFavouriteIcon(checked));
                item.setChecked(!checked);
                return true;
            case R.id.actionShare:
                mActionDelegate.share(this, "Share Body Here");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        showBack();

        showProgressDialog("Loading...");

        mAdvertDetailList.setHasFixedSize(true);
        mAdvertDetailList.setLayoutManager(new LinearLayoutManager(this));
        mAdvertDetailAdapter = new AdvertDetailAdapter(getLayoutInflater());
        mAdvertDetailAdapter.setHistoryClickListener(new AdvertDetailAdapter.OnAdvertHistoryClickListener() {
            @Override
            public void onClicked() {
                mPresenter.onOpenAdvertHistory();
            }
        });
        mAdvertDetailList.setAdapter(mAdvertDetailAdapter);
        mCollapsingToolbarLayout.setTitleEnabled(false);
        mAppBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                boolean offsetChanged = Math.abs(verticalOffset) >= (appBarLayout.getTotalScrollRange() - mToolbar.getHeight());
                mPresenter.onScrollOffsetChanged(offsetChanged);
            }
        });

        AdvertPagerAdapter adapter = new AdvertPagerAdapter(getSupportFragmentManager());
        for (int i = 0; i < IMAGES.length; i++) {
            adapter.addFragment(ImageFragment.newInstance(IMAGES[i], i));
        }

        mPager.setAdapter(adapter);
        mPresenter.onLoadAdvert();

        mBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getAction().equalsIgnoreCase(Constants.ACTION_UPDATE_IMAGE_POSITION)) {
                    mPager.setCurrentItem(intent.getIntExtra(Constants.KEY_IMAGE_POSITION, 0));
                }
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcastReceiver, new IntentFilter(Constants.ACTION_UPDATE_IMAGE_POSITION));
    }

    @Override
    protected void onDestroy() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcastReceiver);
        super.onDestroy();
    }

    @Override
    public void onTitleChanged(String title) {
        setTitle(title);
    }

    @Override
    public void onSuccessfullyLoadAdvert(List<AdvertInfo> result) {
        if (result != null) {
            mPresenter.setTitle(String.valueOf(result.get(0).getModel()));
            mAdvertDetailAdapter.addAdvertDetails(result);
        }
    }

    @Override
    public void onLoadAdvertFailure(String errorMessage) {
        showToast(errorMessage);
    }

    @Override
    public void onLoadAdvertDone() {
        hideProgressDialog();
    }

    @Override
    public void onShowToast(int resId) {
        showToast(resId);
    }

    @Override
    public void openHistoryActivity() {
        openActivity(HistoryCheckActivity.class);
    }

    @OnClick(R.id.actionCall)
    void onCallClicked() {
        mActionDelegate.call(this, "074788888899");
    }

    @OnClick(R.id.actionSMS)
    void onSMSClicked() {
        mActionDelegate.sendMessage(this, "07556565687", "Message body here");
    }

    @OnClick(R.id.actionEmail)
    void onEmailClicked() {
        openActivity(EmaiAdvertiserActivity.class);
    }
}
