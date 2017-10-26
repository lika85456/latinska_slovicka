package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by lika85456 on 24.10.2017.
 */
public class StorageHandler {
    public Context ctx;
    public StorageHandler(Context ctx){
        this.ctx = ctx;
    }

    public void save(String filename, String content)
    {
        FileOutputStream outputStream;
        try {
            outputStream = this.ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Log.d("Error","Error while saving words");
        }

    }

    public String load(String filename)
    {
        try
        {
            FileInputStream fis = ctx.openFileInput(filename);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
        catch(Exception e)
        {
            Log.d("Erorr","Všechno až na"+e.toString()+ " Je  v naprostém pořádku :)");
        }

        return null;
    }
}
