package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by lika85456 on 06.04.2017.
 * Category class, contains array of words
 *
 *
 * STRUCTURE
 * category.txt
 * (ID) Name
 *
 * s(ID).txt
 * (ImageID) CZ|LAT
 *
 * s(ID)_background - background of (ID) category
 *
 * a(0).png = image of word
 */
public class Category {
    public String name;
    public Word[] words;
    public Drawable image;
    public int id;

    public Category(String name,int id)
    {
        //PARSING FILES SHITS
        this.name = name;
        this.id = id;
        String[] lines = Loader.loadFile("s"+id);
        ArrayList<Word> temp = new ArrayList<Word>();

        for(int i = 0; i< lines.length; i++)
        {
            String line = lines[i];
            //(ImageID) (Text)
            //0 canis|pes
            int placeWhereSpaceIs = line.indexOf(" ");
            int imageID = Integer.parseInt(line.substring(0,placeWhereSpaceIs));
            String text = line.substring(placeWhereSpaceIs,line.length());
            String[] splited =  text.split("\\|");
            temp.add(new Word(splited[0], splited[1],Loader.loadDrawable("a"+String.valueOf(imageID))));
        }
        this.words = temp.toArray(new Word[0]);
        this.image = Loader.loadDrawable("s"+id+"_background");
    }
    public static ArrayList<Category> getCategories(Context context)
    {
        ArrayList<Category> temp = new ArrayList<Category>();
        String[] lines = Loader.loadFile("category");
        for(String line : lines)
        {
            int placeWhereFirstSpaceIs = line.indexOf(" ");
            temp.add(new Category(line.substring(placeWhereFirstSpaceIs,line.length()),Integer.parseInt(line.substring(0,placeWhereFirstSpaceIs))));
        }
        return temp;
    }

/*
 THIS IS OLD SHIT, JUST LOADING ALL FILES IN /raw :)
 public static ArrayList<Category> getCategories(Context context)
    {
        ArrayList<Category> temp = new ArrayList<Category>();
        Field[] fields=R.raw.class.getFields();
        for(int count=0; count < fields.length; count++){
            try {
                int resourceID=fields[count].getInt(fields[count]);
                String name = context.getResources().getResourceName(resourceID);
                if(Integer.getInteger(name)==null)
                temp.add(new Category(name));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return temp;
    }

*/
    public int getNumberOfWords()
    {
        return this.words.length;
    }
}
