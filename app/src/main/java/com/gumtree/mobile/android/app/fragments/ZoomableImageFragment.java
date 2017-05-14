package com.gumtree.mobile.android.app.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.widgets.TouchImageView;
import com.gumtree.mobile.android.app.base.BaseFragment;
import com.gumtree.mobile.android.data.pojo.Constants;

import butterknife.BindView;

/**
 * Created by Filippo on 4/5/2017.
 */

public class ZoomableImageFragment extends BaseFragment {

    @BindView(R.id.imageViewPhoto) protected TouchImageView mPhotoView;
    private int mAdvertImagePosition;

    public static ZoomableImageFragment newInstance(int imageId, int position) {

        Bundle args = new Bundle();
        args.putInt(Constants.KEY_IMAGE_ID, imageId);
        args.putInt(Constants.KEY_IMAGE_POSITION, position);
        ZoomableImageFragment fragment = new ZoomableImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void bindView(View view) {
        super.bindView(view);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mPhotoView.setTransitionName("image--"+getArguments().getInt(Constants.KEY_IMAGE_POSITION, 0));
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_zoomable_image;
    }

    @Override
    protected void onViewReady(Bundle savedInstanceState, Bundle arguments) {
        super.onViewReady(savedInstanceState, arguments);
        mAdvertImagePosition = arguments.getInt(Constants.KEY_IMAGE_POSITION, 0);

//        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/" + arguments.getInt(Constants.KEY_IMAGE_ID, 0));
//        mPhotoView.setPhotoUri(uri);

        Glide.with(this)
                .load(arguments.getInt(Constants.KEY_IMAGE_ID, 0))
                .fitCenter()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(mPhotoView);
    }

//    @OnClick(R.id.imageViewAdvert)
//    void onImageClicked() {
//        Toast.makeText(getContext(), "Image Clicked on Position! -> " + mAdvertImagePosition, Toast.LENGTH_SHORT).show();
//        ViewPagerActivity.start(getContext());
//    }
}
