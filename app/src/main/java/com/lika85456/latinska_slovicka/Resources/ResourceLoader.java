package com.lika85456.latinska_slovicka.Resources;

/**
 * Created by lika85456 on 04.04.2017.
 * Resource loader, it could be static, but it needs to be initialized to Resources first
 * Loads images, icons, text, and all words (WORDS ARE LOADED BY WordLoader!!)
 * <p/>
 * STRUCTURE:
 * some_category_of_words = directory in res?
 * some_category_of_words/icon.xxx = icon for category
 * some_category_of_words/1.xxx = icon for word
 * some_category_of_words/words.txt = words
 */
public class ResourceLoader {
    public static void load(String category) {
        //TODO load images + words from wordloader (Resources.categoryWords = WordLoader.load(path))
    }
}
