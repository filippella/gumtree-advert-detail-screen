package com.gumtree.mobile.android.app.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.gumtree.mobile.android.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Filippo on 4/4/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Nullable @BindView(R.id.toolbar) protected Toolbar mToolbar;

    private Unbinder mUnbinder;
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        resolveDependency();
        super.onCreate(savedInstanceState);

        int contentRes = getContentView();
        if (contentRes > 0) {
            setContentView(contentRes);
        }
        mUnbinder = ButterKnife.bind(this);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            int statusBarColor = getStatusBarColor();
            if (statusBarColor > 0) {
                changeStatusBarColor(statusBarColor);
            }
        }

        onViewReady(savedInstanceState, getIntent());
    }

    protected void resolveDependency() {}

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {}

    protected void changeStatusBarColor(int statusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this, statusBarColor));
        }
    }

    @LayoutRes protected abstract int getContentView();

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    protected int getStatusBarColor() {
        return 0;
    }

    protected void showProgressDialog(String message) {
        if(mDialog == null) {
            mDialog = new ProgressDialog(this);
            mDialog.setMessage(message);
            mDialog.setCancelable(true);
            mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        }
        mDialog.show();
    }

    protected void hideProgressDialog() {
        if(mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    protected void openActivity(Class<?> activityClazz) {
        startActivity(new Intent(this, activityClazz));
    }

    protected void showToast(int resId) {
        Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    protected void showBack() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
