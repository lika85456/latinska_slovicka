package com.lika85456.latinska_slovicka.Resources;

import com.lika85456.latinska_slovicka.Global;

import java.io.InputStream;

/**
 * Created by lika85456 on 04.04.2017.
 * Resource loader, it could be static, but it needs to be initialized to Resources first
 * Loads images, icons, text, and all words (WORDS ARE LOADED BY WordLoader!!)
 * <p/>
 * STRUCTURE:
 * some_category_of_words = directory in res?
 * some_category_of_words/icon.xxx = icon for category
 * some_category_of_words/i154.xxx = icon for word
 * some_category_of_words/words.txt = words
 */
public class Loader {
    public static InputStream getInputStream(String path) {
        //        0   1      2       3      4
        //Path = res/raw/category/zviratka/name
        String[] splited = path.split("\\/"); // split by / (\ = escape char) wich is escaped by \ xDDD
        return Global.context.getResources().openRawResource(
                Global.context.getResources().getIdentifier(splited[3],
                        "raw/category/" + splited[2], Global.context.getPackageName()));
    }
}
