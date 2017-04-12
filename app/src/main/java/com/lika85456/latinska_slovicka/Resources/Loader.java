package com.lika85456.latinska_slovicka.Resources;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import com.lika85456.latinska_slovicka.Global;

import java.io.BufferedReader;
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
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String[] loadFile(String path) {
        //Path = zviratka
        //String[] splited = path.split("\\/"); // split by / (\ = escape char) wich is escaped by \ xDDD

         InputStream is = Global.context.getResources().openRawResource(
                Global.context.getResources().getIdentifier(path,
                        "raw", Global.context.getPackageName()));

        String line;
        ArrayList<String> s = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            while ((line = br.readLine()) != null) {
                s.add(line);
            }
        }
        catch(Exception e){
            Log.d("Error",e.toString());
        }
        return s.toArray(new String[0]);
    }
}
