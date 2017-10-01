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
public class Category{
    public String name;
    public Word[] words;
    public Drawable image;
    public int id;
    public int[] range;

    //indicating if selected in CategoryPickerActivity
    public Boolean selected = false;
    public Category(String s)
    {
        String[] splited = s.split("\\|");
        this.name = splited[0];
        this.id = Integer.parseInt(splited[1]);
        this.range = stringToArray(splited[2]);
        init();
    }
    public Category(String name,int id,int[] range)
    {
        this.name = name;
        this.id = id;
        this.range = range;
        init();
    }

    public void init()
    {
        //PARSING FILES SHITS
        String[] lines = Loader.loadFile("slovicka");
        ArrayList<Word> temp = new ArrayList<Word>();

        for(int i = 0; i< lines.length; i++)
        {
            String line = lines[i];
            //(ImageID) (Text)
            //0 canis|pes
            int placeWhereFirstSpaceIs = line.indexOf(" ");
            int imageID = Integer.parseInt(line.substring(0,placeWhereFirstSpaceIs));
            if(!isInArray(range,imageID))
                continue;
            String text = line.substring(placeWhereFirstSpaceIs,line.length());
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
            String name = line.substring(placeWhereFirstSpaceIs,line.indexOf("|"));
            int id = Integer.parseInt(line.substring(0,placeWhereFirstSpaceIs));
            int[] range = makeRange(line.split("\\|")[1]);
            Category c = new Category(name,id,range);
            temp.add(c);
        }
        return temp;
    }

    public String toString()
    {
        return this.name+"|"+this.id+"|"+arrayToString(this.range);
    }
    public String arrayToString(int[] array)
    {
        String ret = "{";
        for(int i =0;i<array.length;i++)
        {
            ret+=array[i]+",";
        }
        return ret+"}";
    }
    public int[] stringToArray(String s)
    {
        s = s.substring(1,s.length()-1);
        String[] splited = s.split(",");
        int[] arr = new int[splited.length];
        for(int i = 0;i < splited.length; i++)
        {
            arr[i] = Integer.parseInt(splited[i]);
        }
        return arr;
    }

    public static int[] makeRange(String s)
    {
        //0-31,35
        String[] splited = s.split(",");
        ArrayList<String> ar = new ArrayList<String>();
        for(String line:splited)
        {
            if(line.contains("-"))
            {
                String[] sslited = line.split("-");
                for(int i = Integer.parseInt(sslited[0]);i<Integer.parseInt(sslited[1]);i++)
                {
                    ar.add(String.valueOf(i));
                }
            }
            else
                ar.add(line);
        }
        int[] ret = new int[ar.size()];
        for(int i = 0;i<ar.size();i++)
        {
            ret[i] = Integer.parseInt(ar.get(i));
        }
        return ret;
    }

    public static Boolean isInArray(int[] array,int id)
    {
        for(int searched:array)
        {
            if(searched==id)
                return true;

        }
        return false;
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
