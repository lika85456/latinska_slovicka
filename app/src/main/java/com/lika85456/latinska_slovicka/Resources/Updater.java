package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.util.Log;

import com.lika85456.latinska_slovicka.NetworkUtils;

import java.io.IOException;

interface IUpdater{
    void run();
}

public class Updater implements IUpdater{
    public Context ctx;
    public Updater(Context ctx) {
        this.ctx = ctx;
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
                try {
                    http.saveImage("http://lika85456.4fan.cz/latina/a" + id + ".png", "a" + id);
                } catch (IOException e) {
                    Log.e("Loading images fucked", e.toString());
                }
            }

            return true;
        } else {
            return false;
        }
    }
    
    public void run()
    {
    
    }
}
