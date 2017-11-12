package com.lika85456.latinska_slovicka.Resources;

/**
 * Created by lika85456 on 08.11.2017.
 */
public class Word {
    public int id;
    public String cz, la;

    public Word(int id, String cz, String la)
    {
        this.id = id;
        this.cz = cz;
        this.la = la;
    }

    /***
     * Makes word from line from slovicka.txt
     *
     * @param s line from slovicka.txt
     */
    public Word(String s) {
        //0 voda|aqua
        int indexOfFirstSpace = s.indexOf(" ");
        this.id = Integer.parseInt(s.substring(0, indexOfFirstSpace));
        String[] splited = s.substring(indexOfFirstSpace, s.length()).split("\\|");
        this.cz = splited[0];
        this.la = splited[1];
    }

    public String toString() {
        return this.id + " " + this.cz + "|" + this.la;
    }
}
