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
    //Name of the category
    public String name;
    //Id of resource with category image
    public int image;
    //category id
    public int id;
    //array of word ids
    public int[] range;

    //indicating if selected in CategoryPickerActivity
    public Boolean selected = false;
    //Empty costructor
    public Category()

    public Category(String name,int id,int[] range)
    {
        this.name = name;
        this.id = id;
        this.range = range;
    }
    public String toString()
    {
        return this.name + "|" + this.id + "|" + arrayToString(this.range);
    }

    //rreverse of toStrging function
    public void fromString()
 
    public int getNumberOfWords()
    {
        return this.range.length;
    }
}
