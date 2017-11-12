package com.lika85456.latinska_slovicka.Resources;

/**
 * Category data structure
 * Created by lika85456 on 08.11.2017.
 */
public class Category {
    public String name;
    public int id;
    public int[] range;
    public String sRange;

    // for arrayAdapter in categoryPicker
    public Boolean selected = false;

    public Category(int id, String name, int[] range) {
        this.name = name;
        this.id = id;
        this.range = range;
    }


    public Category() {

    }

    /***
     * Makes category from string in category.txt
     *
     * @param s Line of category.txt
     */
    public Category(String s) {
        String[] splited = s.split("\\|");
        this.name = splited[0].substring(splited[0].indexOf(" ") + 1);
        this.id = CategoryHandler.parseInt(CategoryHandler.convertStringToUTF8(splited[0]).substring(0, CategoryHandler.convertStringToUTF8(splited[0]).indexOf(" ")));
        this.range = CategoryHandler.makeRange(CategoryHandler.convertStringToUTF8(splited[1]));
        this.sRange = CategoryHandler.convertStringToUTF8(splited[1]);
    }

    public String toString() {
        return id + " " + name + "|" + sRange + "\n";
    }

    public int getWordCount() {
        return range.length;
    }
}
