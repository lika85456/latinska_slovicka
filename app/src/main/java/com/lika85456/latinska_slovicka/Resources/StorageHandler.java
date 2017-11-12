package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.lika85456.latinska_slovicka.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lika85456 on 24.10.2017.
 * This is helper with saving and loading files in internal storage
 */
public class StorageHandler {
    public Context ctx;
    public StorageHandler(Context ctx){
        this.ctx = ctx;
    }

    /***
     * Save file
     *
     * @param filename
     * @param content
     */
    public void save(String filename, String content)
    {
        FileOutputStream outputStream;
        try {
            outputStream = this.ctx.openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(content.getBytes());
            outputStream.close();
        } catch (Exception e) {
            Log.d("StorageHandler", "Error while saving words");
        }

    }

    /***
     * Load file
     * @param filename
     * @return
     */
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
                sb.append(line + "\n");
            }
            return sb.toString();
        }
        catch(Exception e) {
            Log.d("StorageHandler", "Všechno až na" + e.toString() + " Je  v naprostém pořádku :)");
        }

        return null;
    }

    public int getResourceId(String name) {
        int resId = 0;
        try {
            resId = R.raw.class.getField(name).getInt(null);

        } catch (Exception e) {
            Log.d("Exception", e.toString());

        }
        return resId;
    }

    public String saveBitmapToInternalStorage(Bitmap bitmapImage, String name) {
        ContextWrapper cw = new ContextWrapper(this.ctx.getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, name);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                Log.d("Erorr", e.toString());
            }
        }
        return directory.getAbsolutePath() + "|" + name;
    }

    public Drawable loadImageFromStorage(String name) {
        try {
            File f = new File("", name);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return new BitmapDrawable(ctx.getResources(), b);
        } catch (FileNotFoundException e) {
            Log.d("Erorr", e.toString());
        }
        return null;
    }
}