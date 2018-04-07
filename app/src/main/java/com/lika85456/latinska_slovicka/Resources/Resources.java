package com.lika85456.latinska_slovicka.Resources;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by lika85456 on 08.11.2017.
 * Class managing with slovicka and category resources
 */
public class Resources {
    public String words;
    public String category;

    /***
     * Makes Resources class again from s string
     *
     * @param s string made from toString method of this class
     */
    public Resources(String s) {
        String[] splited = s.split("€");
        this.words = splited[0];
        this.category = splited[1];
    }

    public Resources() {

    }

    public int[] getRangeFromCategories(Category[] categories) {

        int size = 0;
        for (int i = 0; i < categories.length; i++) {
            size += categories[i].getWordCount();
        }

        int[] categoriesRange = new int[size];

        int index = 0;
        for (int i = 0; i < categories.length; i++) {
            int[] range = categories[i].getRange();
            for (int x = 0; x < range.length; x++) {
                categoriesRange[index] = range[x];
                index += 1;
            }
        }
        return categoriesRange;
    }

    public Category getCategory(int id) {
        return new Category(category.split("\n")[id]);
    }

    public Category[] getAllCategories() {
        return resourceToArray(this.category);
    }

    public Category[] getCategories(String stringToParse) {
        return resourceToArray(stringToParse);
    }

    public Word[] getAllWords() {
        String[] splited = words.split("\\n");
        ArrayList<Word> alWord = new ArrayList<Word>();
        for (int i = 0; i < splited.length; i++) {
            String line = splited[i];
            if (line.startsWith("//")) continue;
            alWord.add(new Word(line));
        }
        return alWord.toArray(new Word[0]);
    }

    /**
     * Returns word array of words in range array
     *
     * @param range range array
     * @return array of words
     */
    public Word[] getWordsFromRange(int[] range) {
        int length = range.length;
        Word[] words = getAllWords();
        Word[] toRet = new Word[length];
        for (int i = 0; i < length; i++) {
            toRet[i] = (words[range[i]]);
        }
        return toRet;
    }

    /***
     * Makes array of categories from Resources.category
     *
     * @param category Resources.category string
     * @return array of all categories
     */
    private Category[] resourceToArray(String category) {
        String[] splited = category.split("\\n");
        Category[] al = new Category[splited.length];
        int numberOfComments = 0;
        for (int i = 0; i < al.length; i++) {
            String line = splited[i];
            if (line.startsWith("//") || line.replaceAll("\\s+", "").equals("")) {
                numberOfComments++;
                continue;
            }
            al[i - numberOfComments] = new Category(line);
        }
        return Arrays.copyOfRange(al, 0, al.length - numberOfComments);
    }

    public String toString() {
        return (this.words + "€" + this.category);
    }
}
