package com.gumtree.mobile.android.app.delegates;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import javax.inject.Inject;

/**
 * Created by Filippo on 4/6/2017.
 */

public class AdvertActionDelegate {

    @Inject
    public AdvertActionDelegate() {
    }

    public void share(Context context, String shareBody) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Best Amharic Keyboard");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        sharingIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        context.startActivity(sharingIntent);
    }

    public void call(Context context, String telephone) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + Uri.encode(telephone.trim())));
        callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(callIntent);
    }

    public void sendMessage(Context context, String telephone, String messageBody) {
        Uri uri = Uri.parse("smsto:" + telephone);
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", messageBody);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
