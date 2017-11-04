package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

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
        this.id = parseInt(splited[1]);
        this.range = makeRange(splited[2]);
        init("");
    }

    public Category(String categoryLine,String slovicka)
    {
        String[] splited = categoryLine.split("\\|");
        this.name = splited[0].substring(3);
        this.id = parseInt(convertStringToUTF8(splited[0]).substring(0, convertStringToUTF8(splited[0]).indexOf(" ")));
        this.range = makeRange(convertStringToUTF8(splited[1]));
        init(slovicka);
    }

    public Category(String name,int id,int[] range)
    {
        this.name = name;
        this.id = id;
        this.range = range;
        init("");
    }

    public static Category[] loadFromResponses(String slovicka,String category)
    {
        String[] categoryLines = category.split("\\n");
        ArrayList<Category> tList = new ArrayList<Category>();
        for(String categoryLine:categoryLines)
        {
            Category temporaryCategory = new Category(categoryLine,slovicka);
            tList.add(temporaryCategory);
        }
        return tList.toArray(new Category[0]);
    }


    public void init(String slovicka)
    {
        //PARSING FILES
        String[] lines = Loader.loadFile("slovicka");
        if(!slovicka.equals(""))
        {
            lines = slovicka.split("\\n");
        }
        ArrayList<Word> temp = new ArrayList<Word>();

        for(int i = 0; i< lines.length; i++)
        {
            String line = lines[i];
            //(ImageID) (Text)
            //0 canis|pes
            int placeWhereFirstSpaceIs = line.indexOf(" ");
            int imageID = parseInt(line.substring(0,placeWhereFirstSpaceIs));
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
        StorageHandler sH = new StorageHandler(context);
        String sloviska = sH.load("slovicka");
        if(sloviska!=null  && !sloviska.equals(""))
        {
            return new ArrayList<Category>(Arrays.asList(Category.makeArrayFromString(sloviska)));
        }

        ArrayList<Category> temp = new ArrayList<Category>();
        String[] lines = Loader.loadFile("category");
        for(String line : lines)
        {
            int placeWhereFirstSpaceIs = line.indexOf(" ");
            String name = line.substring(placeWhereFirstSpaceIs,line.indexOf("|"));
            int id = parseInt(line.substring(0,placeWhereFirstSpaceIs));
            int[] range = makeRange(line.split("\\|")[1]);
            Category c = new Category(name,id,range);
            temp.add(c);
        }
        return temp;
    }

    public static String arrayToString(Category[] categories)
    {
        String toPass="";
        for(int i=0;i<categories.length;i++)
        {
            if(categories[i].selected==true)
            {
                toPass+=(categories[i]).toString()+"€";

            }
        }
        return toPass;
    }

    public static Category[]  makeArrayFromString(String toParse)
    {
        ArrayList<Category> categories = new ArrayList<Category>();

        String[] toParseA = toParse.split("€");
        for(int i=0;i<toParseA.length;i++)
        {
            categories.add(new Category(toParseA[i]));
        }
        Category[] categoriesA = categories.toArray(new Category[0]);
        return categoriesA;

    }

    public String toString()
    {
        return this.name+"|"+this.id+"|"+arrayToString(this.range);
    }
    public String arrayToString(int[] array)
    {
        String ret = "";
        for(int i =0;i<array.length;i++)
        {
            ret+=array[i]+",";
        }
        return ret;
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
                for(int i = parseInt(sslited[0]);i<parseInt(sslited[1])+1;i++)
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
            ret[i] = parseInt(ar.get(i));
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

    public static int parseInt(String s)
    {

            // strips off all non-ASCII characters
            s = s.replaceAll("[^\\x00-\\x7F]", "");

            // erases all the ASCII control characters
            s = s.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

            // removes non-printable characters from Unicode
            s = s.replaceAll("\\p{C}", "");

            s.trim();

        return Integer.parseInt(convertStringToUTF8(s));
    }

    public static String convertStringToUTF8(String s)
    {
        try
        {
            byte[] utfString = s.getBytes("UTF-8");
            Log.d("Converting string","Length is: "+utfString.length+" the string is: "+s);
            for(byte ss:utfString)
            {
                Log.d("Printing...",String.valueOf(ss));
            }
            return new String(utfString,"UTF-8");
        }
        catch(Exception e)
        {
            Log.e("Error,",e.toString());
        }
        return null;
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
