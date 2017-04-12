package com.lika85456.latinska_slovicka.Resources;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lika85456.latinska_slovicka.Global;
import com.lika85456.latinska_slovicka.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by lika85456 on 04.04.2017.
 * Resource loader, it could be static, but it needs to be initialized to Resources first
 * Loads images, icons, text, and all words (WORDS ARE LOADED BY WordLoader!!)
 * <p/>
 * STRUCTURE:
 * some_category_of_words = directory in res?
 * some_category_of_words/icon.xxx = icon for category
 * some_category_of_words/i154.xxx = icon for word
 * some_category_of_words/words.txt = words
 */
public class Loader {
    public static String[] loadFile(String path) {
        int resId;
        try {
            resId = R.raw.class.getField(path).getInt(null);
            return Loader.LoadText(resId);
        }
        catch(Exception e)
        {
            Log.d("Exception",e.toString());
        }
        return null;
    }

    public static Drawable loadDrawable(String path)
    {
        int resId;
        try {
            resId = R.raw.class.getField(path).getInt(null);
            return Loader.LoadDrawable(resId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Drawable LoadDrawable(int id)
    {
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP){
            return Global.context.getResources().getDrawable(id, Global.context.getTheme());
        } else {
            return Global.context.getResources().getDrawable(id);
        }
    }

    public static String[] LoadText(int resourceId) {
        // The InputStream opens the resourceId and sends it to the buffer
        InputStream is = Global.context.getResources().openRawResource(resourceId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String readLine = null;
        ArrayList<String> s = new ArrayList<String>();
        try {
            // While the BufferedReader readLine is not null
            while ((readLine = br.readLine()) != null) {
                s.add(readLine);
            }

            // Close the InputStream and BufferedReader
            is.close();
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return s.toArray(new String[0]);
    }
}
