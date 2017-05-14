package com.gumtree.mobile.android.app.fragments;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.activities.ViewPagerActivity;
import com.gumtree.mobile.android.app.base.BaseFragment;
import com.gumtree.mobile.android.data.pojo.Constants;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Filippo on 4/5/2017.
 */

public class ImageFragment extends BaseFragment {

    @BindView(R.id.imageViewAdvert) protected ImageView mAdvertImageView;
    private int mAdvertImagePosition;

    public static ImageFragment newInstance(int imageId, int position) {

        Bundle args = new Bundle();
        args.putInt(Constants.KEY_IMAGE_ID, imageId);
        args.putInt(Constants.KEY_IMAGE_POSITION, position);
        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected int getContentView() {
        return R.layout.fragment_advert_image;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Bundle arguments) {
        super.onViewReady(savedInstanceState, arguments);
        mAdvertImagePosition = arguments.getInt(Constants.KEY_IMAGE_POSITION, 0);
        Glide.with(this)
                .load(arguments.getInt(Constants.KEY_IMAGE_ID, 0))
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(mAdvertImageView);
    }

    @OnClick(R.id.imageViewAdvert)
    void onImageClicked() {
        Toast.makeText(getContext(), "Image Clicked on Position! -> " + mAdvertImagePosition, Toast.LENGTH_SHORT).show();
        ViewPagerActivity.start(getActivity(), mAdvertImageView, mAdvertImagePosition);
    }
}
