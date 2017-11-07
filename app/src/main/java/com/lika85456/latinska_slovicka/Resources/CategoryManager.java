package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;
import android.util.Log;

import com.lika85456.latinska_slovicka.NetworkUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by lika85456 on 04.11.2017.
 */
public class CategoryManager {

    public Category[] categories;
    public Context ctx;

    //public Category(String name,int id,int[] range)
    public CategoryManager(Context ctx) {
        this.ctx = ctx;
        this.categories = getCategories(ctx).toArray(new Category[0]);

    }

    public CategoryManager(Context ctx, String categories) {
        this.ctx = ctx;
        this.categories = makeWordIDArrayFromString(categories);
    }

    private static Category[] loadFromResponses(String slovicka, String category) {
        String[] categoryLines = category.split("\\n");
        ArrayList<Category> tList = new ArrayList<Category>();
        for (String categoryLine : categoryLines) {
            Category temporaryCategory = new Category(categoryLine, slovicka);
            tList.add(temporaryCategory);
        }
        return tList.toArray(new Category[0]);
    }

    public static ArrayList<Category> getCategories(Context context) {
        StorageHandler sH = new StorageHandler(context);
        String sloviska = sH.load("slovicka");
        if (sloviska != null && !sloviska.equals("")) {
            return new ArrayList<Category>(Arrays.asList(makeWordIDArrayFromString(sloviska)));
        }

        ArrayList<Category> temp = new ArrayList<Category>();
        String[] lines = Loader.loadFile("category");
        for (String line : lines) {
            int placeWhereFirstSpaceIs = line.indexOf(" ");
            String name = line.substring(placeWhereFirstSpaceIs, line.indexOf("|"));
            int id = parseInt(line.substring(0, placeWhereFirstSpaceIs));
            int[] range = makeRange(line.split("\\|")[1]);
            Category c = new Category(name, id, range);
            temp.add(c);
        }
        return temp;
    }

    public static String CategoryArrayToString(Category[] categories) {
        String toPass = "";
        for (int i = 0; i < categories.length; i++) {
            toPass += (categories[i]).toString() + "€";
        }
        return toPass;
    }

    public static Category[] makeWordIDArrayFromString(String toParse) {
        ArrayList<Category> categories = new ArrayList<Category>();

        String[] toParseA = toParse.split("€");
        for (int i = 0; i < toParseA.length; i++) {
            categories.add(new Category(toParseA[i]));
        }
        Category[] categoriesA = categories.toArray(new Category[0]);
        return categoriesA;

    }

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

    public static int parseInt(String s) {

        // strips off all non-ASCII characters
        s = s.replaceAll("[^\\x00-\\x7F]", "");

        // erases all the ASCII control characters
        s = s.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");

        // removes non-printable characters from Unicode
        s = s.replaceAll("\\p{C}", "");

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

    public void update() {
        if (NetworkUtils.isWifiConnected(ctx)) {
            HttpHandler http = new HttpHandler();
            String slovicka = http.getRequest("http://lika85456.4fan.cz/slovicka.txt", this.ctx);
            http = new HttpHandler();
            String category = http.getRequest("http://lika85456.4fan.cz/category.txt", this.ctx);
            this.categories = loadFromResponses(slovicka, category);
            String categoriesInString = CategoryArrayToString(categories);
            StorageHandler sHandler = new StorageHandler(ctx);
            sHandler.save("slovicka", categoriesInString);

        }
    }

    public String toString() {
        return CategoryArrayToString(this.categories);
    }
}
