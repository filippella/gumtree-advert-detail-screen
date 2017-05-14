package com.gumtree.mobile.android.app.utilities;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;

/**
 * Created by Filippo on 4/6/2017.
 */

public final class DrawableUtils {

    private DrawableUtils() {
        throw new IllegalStateException("Can't instantiate a utility class.");
    }

    public static void tintDrawable(Context context, Drawable drawable, int colorTint) {
        int color = ContextCompat.getColor(context, colorTint);
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);
        DrawableCompat.setTintMode(wrappedDrawable, PorterDuff.Mode.SRC_IN);
    }
}
