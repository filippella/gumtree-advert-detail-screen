package com.gumtree.mobile.android.app.adpters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gumtree.mobile.android.app.R;
import com.gumtree.mobile.android.app.base.BaseClickListener;
import com.gumtree.mobile.android.app.base.BaseViewHolder;
import com.gumtree.mobile.android.app.holders.HolderAdvertDescription;
import com.gumtree.mobile.android.app.holders.HolderAdvertDetail;
import com.gumtree.mobile.android.app.holders.HolderAdvertFinance;
import com.gumtree.mobile.android.app.holders.HolderAdvertHistory;
import com.gumtree.mobile.android.app.holders.HolderAdvertLocationAndPrice;
import com.gumtree.mobile.android.app.holders.HolderAdvertProfileLink;
import com.gumtree.mobile.android.app.holders.HolderAdvertTitle;
import com.gumtree.mobile.android.data.pojo.AdvertInfo;
import com.gumtree.mobile.android.data.pojo.AdvertInfoType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Filippo on 4/5/2017.
 */

public class AdvertDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final List<AdvertInfo> mAdvertInfos = new ArrayList<>();

    public AdvertDetailAdapter(LayoutInflater inflater) {
        mLayoutInflater = inflater;
    }

    @Override
    public int getItemViewType(int position) {
        return mAdvertInfos.get(position).getType().getId();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder;
        switch (AdvertInfoType.fromId(viewType)) {
            case TYPE_TITLE:
                holder = new HolderAdvertTitle(mLayoutInflater.inflate(R.layout.item_advert_title, parent, false));
                break;
            case TYPE_LOCATION_AND_PRICE:
                holder = new HolderAdvertLocationAndPrice(mLayoutInflater.inflate(R.layout.item_advert_location_and_price, parent, false));
                break;
            case TYPE_DETAIL:
                holder = new HolderAdvertDetail(mLayoutInflater.inflate(R.layout.item_advert_detail_layout, parent, false), R.mipmap.ic_list_white_24dp);
                break;
            case TYPE_FINANCE:
                holder = new HolderAdvertFinance(mLayoutInflater.inflate(R.layout.item_advert_finance_layout, parent, false));
                break;
            case TYPE_DESCRIPTION:
                holder = new HolderAdvertDescription(mLayoutInflater.inflate(R.layout.item_advert_detail_layout, parent, false), R.mipmap.ic_subject_white_24dp);
                break;
            case TYPE_HISTORY:
                holder = new HolderAdvertHistory(mLayoutInflater.inflate(R.layout.item_advert_history_layout, parent, false));
                holder.setListener(mHistoryClickListener);
                break;
            case TYPE_ADVERTISER_PROFILE:
                holder = new HolderAdvertProfileLink(mLayoutInflater.inflate(R.layout.item_advertiser_profile_layout, parent, false));
                break;
            default:
                throw new IllegalStateException("Unknown Advert Info type.");
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseViewHolder viewHolder = (BaseViewHolder) holder;
        viewHolder.bind(mAdvertInfos.get(position).getModel());
    }

    @Override
    public int getItemCount() {
        return mAdvertInfos.size();
    }

    public void addAdvertDetails(List<AdvertInfo> result) {
        mAdvertInfos.clear();
        mAdvertInfos.addAll(result);
        notifyDataSetChanged();
    }

    private OnAdvertHistoryClickListener mHistoryClickListener;

    public void setHistoryClickListener(OnAdvertHistoryClickListener listener) {
        mHistoryClickListener = listener;
    }

    public interface OnAdvertHistoryClickListener extends BaseClickListener {

        void onClicked();
    }
}
