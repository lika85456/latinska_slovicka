package com.lika85456.latinska_slovicka.Resources;

import android.util.Log;

import java.util.ArrayList;

/**
 * Category data structure
 * Created by lika85456 on 08.11.2017.
 */
public class Category {
    private String name;
    private int id;
    private String sRange;
    private int[] range;

    public Category(int id, String name, int[] range) {
        this.name = name;
        this.id = id;
        this.range = range;
    }



    /***
     * Makes category from string in category.txt
     *
     * @param s Line of category.txt
     */
    public Category(String s) {
        String[] splited = convertStringToUTF8(s).split("\\|");
        this.name = splited[0].substring(splited[0].indexOf(" ") + 1);
        this.id = parseInt((splited[0]).substring(0, (splited[0]).indexOf(" ")));
        this.sRange = (splited[1]).replaceAll("\\s+", "");
    }



    private static int[] makeRange(String s) {
        //0-31,35
        String[] splited = s.split(",");
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i < splited.length; i++) {
            if (splited[i].contains("-")) {
                String[] invervalSplited = splited[i].split("-");
                int start = parseInt(invervalSplited[0]);
                int end = parseInt(invervalSplited[1]) + 1;
                for (int x = start; x < end; x++) {
                    arrayList.add(x);
                }
            } else {
                arrayList.add(parseInt(splited[i]));
            }

        }
        int[] ret = new int[arrayList.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = arrayList.get(i);
        }
        return ret;
    }

    public static int parseInt(String s) {
        if (s.equals("")) {
            s = "";
        }
        // strips off all non-ASCII characters
        //s = s.replaceAll("[^\\x00-\\x7F]", "");

        // erases all the ASCII control characters
        //s = s.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

        // removes non-printable characters from Unicode
        //s = s.replaceAll("\\p{C}", "");
        s = s.replaceAll(" ", "");

        //s.trim();
        return Integer.parseInt(s);
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

    public int[] getRange() {
        if (range == null) {
            this.range = makeRange(sRange);
        }
        return this.range;
    }

    public String toString() {
        return id + " " + name + "|" + sRange + "\n";
    }

    public int getWordCount() {
        return getRange().length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
