package com.lika85456.latinska_slovicka.Resources;

import android.util.Log;

import java.util.ArrayList;

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
        this.id = parseInt(convertStringToUTF8(splited[0]).substring(0, convertStringToUTF8(splited[0]).indexOf(" ")));
        this.range = makeRange(convertStringToUTF8(splited[1]));
        this.sRange = convertStringToUTF8(splited[1]);
    }

    /***
     * Makes array of categories from Resources.category
     *
     * @param category Resources.category string
     * @return array of all categories
     */
    public static Category[] resourceToArray(String category) {
        String[] splited = category.split("\\n");
        ArrayList<Category> al = new ArrayList<Category>();
        for (String line : splited) {
            al.add(new Category(line));
        }
        return al.toArray(new Category[0]);
    }

    private static int[] makeRange(String s) {
        //0-31,35
        String[] splited = s.split(",");
        ArrayList<String> ar = new ArrayList<String>();
        for (String line : splited) {
            if (line.contains("-")) {
                String[] sslited = line.split("-");
                for (int i = parseInt(sslited[0]); i < parseInt(sslited[1]) + 1; i++) {
                    ar.add(String.valueOf(i));
                }
            } else
                ar.add(line);
        }
        int[] ret = new int[ar.size()];
        for (int i = 0; i < ar.size(); i++) {
            ret[i] = parseInt(ar.get(i));
        }
        return ret;
    }

    public static int parseInt(String s) {

        // strips off all non-ASCII characters
        s = s.replaceAll("[^\\x00-\\x7F]", "");

        // erases all the ASCII control characters
        s = s.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

        // removes non-printable characters from Unicode
        s = s.replaceAll("\\p{C}", "");
        s = s.replaceAll(" ", "");

        s.trim();

        return Integer.parseInt(convertStringToUTF8(s));
    }

    private static String convertStringToUTF8(String s) {
        try {
            byte[] utfString = s.getBytes("UTF-8");
            return new String(utfString, "UTF-8");
        } catch (Exception e) {
            Log.e("Error,", e.toString());
        }
        return null;
    }

    public String toString() {
        return id + " " + name + "|" + sRange + "\n";
    }

    public int getWordCount() {
        return range.length;
    }
}
