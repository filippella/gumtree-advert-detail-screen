package com.gumtree.mobile.android.app.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Filippo on 4/5/2017.
 */

public abstract class BaseViewHolder<M, L extends BaseClickListener> extends RecyclerView.ViewHolder {

    private final Unbinder mUnbinder;
    protected L mListener;

    public BaseViewHolder(View itemView) {
        super(itemView);
        mUnbinder = ButterKnife.bind(this, itemView);
    }

    public void setListener(L listener) {
        mListener = listener;
    }

    protected void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public abstract void bind(M model);
}