package com.lika85456.latinska_slovicka.Resources;

import com.lika85456.latinska_slovicka.Global;

import java.io.InputStream;

/**
 * Created by lika85456 on 04.04.2017.
 * Daddy for loader classes
 */
public class Loader {
    public InputStream getInputStreamByPath(String path) {
        //        0      1       2       3
        //Path = res/category/zviratka/name
        String[] splited = path.split("\/"); // split by / (\ = escape char)
        return Global.context.getResources().openRawResource(
                Global.context.getResources().getIdentifier(splited[3].substring(0, splited[3].indexOf(".")),
                        splited[2], Global.context.getPackageName()));
    }
}
