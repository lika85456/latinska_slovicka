package com.lika85456.latinska_slovicka.Resources;

import android.graphics.drawable.Drawable;

/**
 * Created by lika85456 on 06.04.2017.
 */
public class Word {
    public String cz;
    public String la;
    public Drawable icon;

    public Word(String cz,String la,Drawable icon)
    {
        this.cz = cz;
        this.la = la;
        this.icon = icon;
    }
}
