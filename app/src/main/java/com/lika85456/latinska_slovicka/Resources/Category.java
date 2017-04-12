package com.lika85456.latinska_slovicka.Resources;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lika85456 on 06.04.2017.
 * Category class, contains array of words
 */
public class Category {
    public String name;
    public Word[] words;

    public Category(String name)
    {
        this.name = name;
        //TODO load words
        String[] lines = Loader.loadFile(name);
        ArrayList<Word> temp = new ArrayList<Word>();

        for(int i = 0; i< lines.length; i++)
        {
            Log.d("i",String.valueOf(i));
            String line = lines[i];
            //canis|pes
             String[] splited = line.split("\\|");
             temp.add(new Word(splited[1],splited[0]));
        }

        this.words = temp.toArray(new Word[0]);
    }
}
