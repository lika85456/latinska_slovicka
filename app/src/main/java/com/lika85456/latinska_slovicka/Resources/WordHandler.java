package com.lika85456.latinska_slovicka.Resources;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by lika85456 on 12.11.2017.
 */
public class WordHandler {
    private static Word[] words;

    public WordHandler() {

    }

    public static void loadWords(Context ctx) {
        final Context cttx = ctx;
        new Thread(new Runnable() {
            public void run() {
                String words = new StorageHandler(cttx).load("slovicka");
                String[] splited = words.split("\\n");
                ArrayList<Word> alWord = new ArrayList<Word>();
                for (String line : splited) {
                    alWord.add(new Word(line));
                }
                WordHandler.words = alWord.toArray(new Word[0]);
            }
        }).start();

    }

    public static Word[] getWords() {
        if (WordHandler.words.length > 0) {
            return WordHandler.words;
        } else {
            int MAX_TIME_TO_WAIT = 1000;
            try {
                Thread.sleep(MAX_TIME_TO_WAIT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return WordHandler.words;
        }
    }

    public static String WordsToString() {
        String toReturn = "";
        for (WordHandler.words)
    }

}
