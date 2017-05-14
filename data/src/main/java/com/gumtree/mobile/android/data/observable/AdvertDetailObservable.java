package com.gumtree.mobile.android.data.observable;

import android.util.Pair;

import com.gumtree.mobile.android.data.base.BaseProviderObservable;
import com.gumtree.mobile.android.data.pojo.AdvertInfo;
import com.gumtree.mobile.android.data.pojo.AdvertInfoType;
import com.gumtree.mobile.android.data.pojo.Finance;
import com.gumtree.mobile.android.data.pojo.SimpleAdvertInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Filippo on 4/5/2017.
 */

public class AdvertDetailObservable extends BaseProviderObservable<List<AdvertInfo>> {

    @Inject
    public AdvertDetailObservable() {
    }

    @Override
    protected List<AdvertInfo> doBackgroundWork() throws Exception {
        Thread.sleep(500L);
        List<AdvertInfo> advertInfos = new ArrayList<>();
        advertInfos.add(new SimpleAdvertInfo<String>(AdvertInfoType.TYPE_TITLE, "2006 Volkswagen Golf 2.0 GT TDI Automatic, Full Service History, Full Leather Interior, Long MOT!"));
        advertInfos.add(new SimpleAdvertInfo<Pair<String, Integer>>(AdvertInfoType.TYPE_LOCATION_AND_PRICE, new Pair<String, Integer>("Gorgie, Edinburgh", 695)));
        HashMap<String, String> map = new HashMap<>();
        map.put("Date Posted", "today");
        map.put("Make", "Chrysler");
        map.put("Transmission", "Manual");
        map.put("Model", "PT CRUISER");
        map.put("Year", "2005");
        map.put("Colour", "Black");
        map.put("Engine Size", "2148");
        map.put("Body Type", "Hatchback");
        map.put("Seller Type", "Trade");
        map.put("Mileage", "88000");
        map.put("Fuel type", "Diesel");
        advertInfos.add(new SimpleAdvertInfo<Map<String, String>>(AdvertInfoType.TYPE_DETAIL, map));
        Finance finance = new Finance();
        finance.title = "Looking for car finance?";
        advertInfos.add(new SimpleAdvertInfo<Finance>(AdvertInfoType.TYPE_FINANCE, finance));
        advertInfos.add(new SimpleAdvertInfo<String[]>(AdvertInfoType.TYPE_DESCRIPTION, new String[]{
                "PHONE 07747070236 TO VIEW",
                "CHRYSLER PT CRUISER 2.2 CRD LIMITED \" DIESEL\" 54 REG",
                "MOT AUGUST 2017",
                "88,000 MILES",
                "HALF LEATHER INTERIOR",
                "ALLOY WHEELS",
                "CENTRAL LOCKING",
                "POWER STEERING",
                "ELECTRIC WINDOWS FRONT AND REAR",
                "ELECTRIC MIRRORS",
                "RADIO CD PLAYER",
                "AUTO CLIMATE CONTROL",
                "AIR CON",
                "PRIVACY GLASS",
                "ALL ROUND AIRBAGS",
                "CHEAP TO RUN",
                "PRICED AT £795",
                "PHONE 07747070236 TO VIEW"
        }));
        advertInfos.add(new SimpleAdvertInfo<String>(AdvertInfoType.TYPE_HISTORY, "Check This car's History"));
        advertInfos.add(new SimpleAdvertInfo<String>(AdvertInfoType.TYPE_ADVERTISER_PROFILE, "Car Shop 365 LTD   (open 7 days)'s other ....."));
        return advertInfos;
    }
}
