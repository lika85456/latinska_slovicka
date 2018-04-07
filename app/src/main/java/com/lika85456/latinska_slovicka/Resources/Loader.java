package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.lika85456.latinska_slovicka.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by lika85456 on 14.11.2017.
 */
public class Loader {
    public static boolean loaded = false;
    /***
     * Loads resources into sharedPreferences
     */
    public static void loadResources(final Context ctx) {

        //TODO Load resources
        new Thread(new Runnable() {
            public void run() {
                Resources resources = new Resources();
                String[] slovicka = Loader.loadFile("slovicka", ctx);
                String[] category = Loader.loadFile("category", ctx);
                String ss = "", c = "";
                for (String line : slovicka) {
                    ss += line + "\n";
                }
                for (String line : category) {
                    c += line + "\n";
                }
                resources.words = ss;
                resources.category = c;
                SharedPreferences sharedPref = ctx.getSharedPreferences(
                        "com.lika85456.latinska_slovicka_RESOURCES", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("com.lika85456.latinska_slovicka_RESOURCES", resources.toString());
                editor.commit();

            }
        }).start();

        loaded = true;
    }

    /***
     * Gets resources
     *
     * @return Resources
     */
    public static Resources getResources(Context ctx) {

        SharedPreferences sharedPref = ctx.getSharedPreferences("com.lika85456.latinska_slovicka_RESOURCES", Context.MODE_PRIVATE);
        String ctString = sharedPref.getString("com.lika85456.latinska_slovicka_RESOURCES", "");
        if (ctString.equals("") && loaded == false)
            Loader.loadResources(ctx);
        else if (ctString.equals("") && loaded == true) {
            while (ctString.equals(""))
                ctString = sharedPref.getString("com.lika85456.latinska_slovicka_RESOURCES", "");
        }
        Resources res = new Resources(ctString);

        return res;
    }

    public static boolean isLoaded(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences("com.lika85456.latinska_slovicka_RESOURCES", Context.MODE_PRIVATE);
        String ctString = sharedPref.getString("com.lika85456.latinska_slovicka_RESOURCES", "");
        return !ctString.equals("");
    }


    /***
     * Load file from resources with its name
     *
     * @param path name of the resource
     * @return String array of lines
     */
    private static String[] loadFile(String path, Context ctx) {
        //if(files==null) init();
        int resId = -1;
        try {
            resId = R.raw.class.getField(path).getInt(null);

        } catch (Exception e) {
            Log.d("Exception", e.toString());

        }
        if (0 < resId)
            return LoadText(resId, ctx);
        else
            return null;
    }

    /***
     * Loads drawable by its name
     *
     * @param name of Drawable
     * @return Drawable
     */
    /*private Drawable loadDrawable(String name) {
        //if(files==null) init();
        int resId;
        try {
            resId = R.raw.class.getField(name).getInt(null);
            return LoadDrawable(resId);
        } catch (Exception e) {
            Log.d("ERROL", e.toString());
        }

        return null;
    }
    */

    /***
     * Load drawable by its id
     *
     * @param id id of drawable
     * @return Drawable
     */
    /*private Drawable LoadDrawable(int id) {
        //if(files==null) init();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return this.ctx.getResources().getDrawable(id, this.ctx.getTheme());
        } else {
            return this.ctx.getResources().getDrawable(id);
        }
    }
    */

    /***
     * Load file from resources by its resourceId
     *
     * @param resourceId
     * @return String array of lines
     */
    private static String[] LoadText(int resourceId, Context ctx) {
        //if(files==null) init();
        if (resourceId == -1)
            return null;
        // The InputStream opens the resourceId and sends it to the buffer
        InputStream is = ctx.getResources().openRawResource(resourceId);
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-16")));
        } catch (Exception e) {
            Log.d("ERROR", e.toString());
            return null;
        }
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
            Log.d("Erol", e.toString());
        }
        return s.toArray(new String[0]);
    }

}
