package com.lika85456.latinska_slovicka.Resources;

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
public class Category{
    public String name;
    public Word[] words;
    public Drawable image;
    public int id;
    public int[] range;

    //indicating if selected in CategoryPickerActivity
    public Boolean selected = false;

    //Create new category from category.txt line
    public Category(String s)
    {
        String[] splited = s.split("\\|");
        this.name = splited[0];
        this.id = CategoryManager.parseInt(splited[1]);
        this.range = CategoryManager.makeRange(splited[2]);
        init("");
    }

    public Category(String categoryLine,String slovicka)
    {
        String[] splited = categoryLine.split("\\|");
        this.name = splited[0].substring(3);
        this.id = CategoryManager.parseInt(CategoryManager.convertStringToUTF8(splited[0]).substring(0, CategoryManager.convertStringToUTF8(splited[0]).indexOf(" ")));
        this.range = CategoryManager.makeRange(CategoryManager.convertStringToUTF8(splited[1]));
        init(slovicka);
    }


    public Category(String name,int id,int[] range)
    {
        this.name = name;
        this.id = id;
        this.range = range;
        init("");
    }

    public static Boolean isInArray(int[] array, int id)
    {
        for (int searched : array)
        {
            if (searched == id)
                return true;

        }
        return false;
    }

    public void init(String fileWithWords)
    {
        String[] lines;
        if (fileWithWords == null || fileWithWords == "" || fileWithWords.equals(""))
        {
            lines = Loader.loadFile("slovicka");
        } else
            lines = fileWithWords.split("\\n");
        ArrayList<Word> temp = new ArrayList<Word>();

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            //(ImageID) (Text)
            //0 canis|pes
            int placeWhereFirstSpaceIs = line.indexOf(" ");
            int imageID = CategoryManager.parseInt(line.substring(0, placeWhereFirstSpaceIs));
            if (!isInArray(range, imageID))
                continue;
            String text = line.substring(placeWhereFirstSpaceIs, line.length());
            String[] splited = text.split("\\|");
            temp.add(new Word(splited[0], splited[1], Loader.loadDrawable("a" + String.valueOf(imageID))));
        }
        this.words = temp.toArray(new Word[0]);
        this.image = Loader.loadDrawable("s" + id + "_background");
    }

    public String toString()
    {
        return this.name + "|" + this.id + "|" + arrayToString(this.range);
    }

    private String arrayToString(int[] array)
    {
        String ret = "";
        for (int i = 0; i < array.length; i++)
        {
            ret += array[i] + ",";
        }
        return ret;
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
