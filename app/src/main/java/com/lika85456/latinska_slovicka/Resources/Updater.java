package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.util.Log;

import com.lika85456.latinska_slovicka.NetworkUtils;
import com.lika85456.latinska_slovicka.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

interface IUpdater{
    void run();
}

public class Updater implements IUpdater{
    public Context ctx;
    public Updater(Context ctx) {
        this.ctx = ctx;
    }

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

    /***
     * This method updates the resources
     */
    public Boolean start() {
        //TODO Update all resources
        /** if connected to wifi */
        if (NetworkUtils.isWifiConnected(ctx)) {
            /** download files */
            HttpHandler http = new HttpHandler();
            String slovicka = http.getRequest("http://lika85456.4fan.cz/latina/slovicka.txt", this.ctx);
            http = new HttpHandler();
            String category = http.getRequest("http://lika85456.4fan.cz/latina/category.txt", this.ctx);

            /** save files*/
            StorageHandler sHandler = new StorageHandler(ctx);
            sHandler.save("slovicka", slovicka);
            sHandler.save("category", category);

            /** load and save images**/
            String[] slovickaInArray = slovicka.split("\\n");
            for (String slovicko : slovickaInArray) {
                String id = slovicko.substring(0, slovicko.indexOf(" "));
                http.saveImage("http://lika85456.4fan.cz/latina/a" + id + ".png", "a" + id, ctx);

            }

            return true;
        } else {
            StorageHandler sHandler = new StorageHandler(ctx);
            String[] slovicka = loadFile("slovicka");
            String[] category = loadFile("category");

            String sSlovicka = "", sCategory = "";
            for (String t : slovicka) {
                sSlovicka += t + "\n";
            }

            for (String c : category) {
                sCategory += c + "\n";
            }

            sHandler.save("slovicka", sSlovicka);
            sHandler.save("category", sCategory);
            return false;
        }


    }

    private String[] loadFile(String path) {
        //if(files==null) init();
        int resId = -1;
        try {
            resId = R.raw.class.getField(path).getInt(null);

        } catch (Exception e) {
            Log.d("Exception", e.toString());

        }
        if (0 < resId)
            return Updater.LoadText(resId, this.ctx);
        else
            return null;
    }
    
    public void run()
    {
    
    }
}
