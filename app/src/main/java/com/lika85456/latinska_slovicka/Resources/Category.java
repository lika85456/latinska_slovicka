package com.lika85456.latinska_slovicka.Resources;

import android.util.Log;

/**
 * Created by lika85456 on 06.04.2017.
 * Category class, contains array of words
 */
public class Category {
    public String name;
    public Word[] words;
    public Category(String name)
    {
        //TODO load words
        String[] lines = Loader.loadFile("zviratka");
        for(String line:lines)
        {
            //canis|pes
             String[] splited = line.split("|");
             Log.d("Slovicko",splited[0]);
        }
    }
}
