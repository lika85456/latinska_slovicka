package com.lika85456.latinska_slovicka.Resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lika85456 on 04.04.2017.
 */
public class WordLoader extends Loader {
    /**
     * @param path = path to category folder
     * @return word array structure
     */
    public static Word[] load(String path) {
        //TODO load words from res
        InputStream is = WordLoader.getInputStream("raw/category/" + path + "/words");
        int c = 0;
        while (c != -1) {
            try {
                c = is.read();
            } catch (Exception e) {
                //TODO handle this exception!!!
                e.printStackTrace();
            }
            System.out.println((char) c);
        }
        try {
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
