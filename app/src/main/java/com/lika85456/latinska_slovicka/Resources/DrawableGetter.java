package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.lika85456.latinska_slovicka.R;

/**
 * Created by lika85456 on 12.11.2017.
 */
public class DrawableGetter {
    public static Drawable getWordDrawable(int id, Context ctx) {
        if (id == -1) return null;
        StorageHandler sH = new StorageHandler(ctx);
        Drawable t = sH.loadImageFromStorage("a" + String.valueOf(id));
        if (t != null) return t;
        int resId = -1;
        try {
            resId = R.raw.class.getField("a" + id).getInt(null);
        } catch (IllegalAccessException e) {
            //e.printStackTrace();
        } catch (NoSuchFieldException e) {
            //e.printStackTrace();
        }

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                return ctx.getResources().getDrawable(resId, ctx.getTheme());
            } else {
                return ctx.getResources().getDrawable(resId);
            }
        } catch (Exception e) {
            return null;
        }

    }

    public static Drawable getCategoryDrawable(int id, Context ctx) {
        StorageHandler sH = new StorageHandler(ctx);
        Drawable t = sH.loadImageFromStorage("s" + String.valueOf(id));
        if (t != null) return t;
        int resId = -1;
        try {
            resId = R.raw.class.getField("s" + id).getInt(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                return ctx.getResources().getDrawable(id, ctx.getTheme());
            } else {
                return ctx.getResources().getDrawable(id);
            }
        } catch (Exception e) {
            return null;
        }

    }
}
