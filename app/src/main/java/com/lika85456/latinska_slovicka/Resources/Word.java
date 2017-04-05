package com.lika85456.latinska_slovicka.Resources;

import android.graphics.drawable.BitmapDrawable;

/**
 * Created by lika85456 on 04.04.2017.
 * Word...
 */
public class Word {
    public String cz;
    public String la;
    public BitmapDrawable icon;

    /**
     * @param cz Word in czech
     * @param la Word in latin
     * @param ic Icon of the word (BitmapDrawable)
     */
    public Word(String cz, String la, BitmapDrawable ic) {
        this.cz = cz;
        this.la = la;
        this.icon = icon;
    }
}
