package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
    public final Context ctx;
    /**
     * Wait time in miliseconds
     */
    private final int waitTime = 5000;
    private Resources resources;

    public Loader(Context ctx) {
        this.resources = new Resources();
        this.ctx = ctx;
    }

    /***
     * Loads resources into memory (i hope)
     */
    public void loadResources() {
        //TODO Load resources
        final Resources s = this.resources;
        new Thread(new Runnable() {
            public void run() {
                String[] slovicka = new Loader(ctx).loadFile("slovicka");
                String[] category = new Loader(ctx).loadFile("category");
                String ss = "", c = "";
                for (String line : slovicka) {
                    ss += line + "\n";
                }
                for (String line : category) {
                    c += line + "\n";
                }
                s.slovicka = ss;
                s.category = c;
            }
        }).start();

    }

    /***
     * Gets resources
     *
     * @return Resources
     */
    public Resources getResources() {
        int waitedTime = 0;
        while (resources == null && waitedTime < waitTime) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                Log.e("Error", e.toString());
            }
            waitedTime += 5;
        }
        return resources;
    }


    /***
     * Load file from resources with its name
     *
     * @param path name of the resource
     * @return String array of lines
     */
    private String[] loadFile(String path) {
        //if(files==null) init();
        int resId = -1;
        try {
            resId = R.raw.class.getField(path).getInt(null);

        } catch (Exception e) {
            Log.d("Exception", e.toString());

        }
        if (0 < resId)
            return LoadText(resId);
        else
            return null;
    }

    /***
     * Loads drawable by its name
     *
     * @param name of Drawable
     * @return Drawable
     */
    private Drawable loadDrawable(String name) {
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

    /***
     * Load drawable by its id
     *
     * @param id id of drawable
     * @return Drawable
     */
    private Drawable LoadDrawable(int id) {
        //if(files==null) init();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return this.ctx.getResources().getDrawable(id, this.ctx.getTheme());
        } else {
            return this.ctx.getResources().getDrawable(id);
        }
    }

    /***
     * Load file from resources by its resourceId
     *
     * @param resourceId
     * @return String array of lines
     */
    private String[] LoadText(int resourceId) {
        //if(files==null) init();
        if (resourceId == -1)
            return null;
        // The InputStream opens the resourceId and sends it to the buffer
        InputStream is = this.ctx.getResources().openRawResource(resourceId);
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
