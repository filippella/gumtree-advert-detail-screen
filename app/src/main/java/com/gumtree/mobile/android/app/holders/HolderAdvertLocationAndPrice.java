package com.gumtree.mobile.android.app.holders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.base.BaseClickListener;
import com.gumtree.mobile.android.app.base.BaseViewHolder;

import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Filippo on 4/5/2017.
 */

public class HolderAdvertLocationAndPrice extends BaseViewHolder<Pair<String, Integer>, BaseClickListener> {

    @BindView(R.id.advertLocationTxt) protected TextView mLocationTxt;
    @BindView(R.id.advertPriceTxt) protected TextView mPriceTxt;

    public HolderAdvertLocationAndPrice(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Pair<String, Integer> model) {
        mLocationTxt.setText(model.first);
        mPriceTxt.setText(String.format(Locale.UK, "£%s", String.valueOf(model.second)));
    }

    @OnClick(R.id.locationInfo)
    void onLocationClick(View view) {
        Context context = view.getContext();
        showToast(context, "Opening on the map");

        String latitude = "51.458353";
        String longitude = "-0.305987";

        Uri uri = Uri.parse("http://maps.google.com/maps?daddr=" + latitude + "," + longitude);
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }
}