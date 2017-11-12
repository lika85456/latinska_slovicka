package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by lika85456 on 12.11.2017.
 */
public class CategoryHandler {
    public Category[] categories;

    /***
     * Makes this class again from toString()
     *
     * @param string String made from toString method
     */
    public CategoryHandler(String string) {
        //TODO
        String[] categoryLines = string.split("\\n");
        ArrayList<Category> tCategories = new ArrayList<Category>();
        for (String categoryLine : categoryLines) {
            Category cTemp = new Category(categoryLine);
            tCategories.add(cTemp);
        }

        this.categories = tCategories.toArray(new Category[0]);
    }

    public CategoryHandler(Context ctx) {
        this(new StorageHandler(ctx).load("category"));
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

    public static String convertStringToUTF8(String s) {
        try {
            byte[] utfString = s.getBytes("UTF-8");
            return new String(utfString, "UTF-8");
        } catch (Exception e) {
            Log.e("Error,", e.toString());
        }
        return null;
    }

    /***
     * Makes array of word ids in the category
     *
     * @param s string after the name of category 0 První lekce|0-31,98 -> 0-31,98
     * @return array of word id!s in the category
     */
    public static int[] makeRange(String s) {
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

    /***
     * Converts this class to string
     *
     * @return String of this class
     */
    public String toString() {
        String toPass = "";
        for (int i = 0; i < categories.length; i++) {
            toPass += i + " " + categories[i].name + "|" + categories[i].sRange;
            if (i < categories.length - 1)
                toPass += "\n";
        }
        return toPass;
    }

}
