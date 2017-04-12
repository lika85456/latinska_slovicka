package com.lika85456.latinska_slovicka.Resources;

import android.util.Log;

import com.lika85456.latinska_slovicka.R;

import java.lang.reflect.Field;
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
        this.name = name.split("_")[1];
        //TODO load words
        String[] lines = Loader.loadFile(name);
        ArrayList<Word> temp = new ArrayList<Word>();

        for(int i = 0; i< lines.length; i++)
        {
            String line = lines[i];
            //canis|pes
             String[] splited = line.split("\\|");
             temp.add(new Word(splited[0],splited[1],Loader.loadDrawable(this.name+"_"+String.valueOf(i))));
        }
        this.words = temp.toArray(new Word[0]);
    }

    public static Category[] getCategories()
    {
        ArrayList<Category> temp = new ArrayList<Category>();
        Field[] fields=R.raw.class.getFields();
        for(int count=0; count < fields.length; count++){
            temp.add(new Category(fields[count].getName()));
        }
        return temp.toArray(new Category[0]);
    }
}
