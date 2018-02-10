package com.lika85456.latinska_slovicka.Resources;

/**
 * Created by lika85456 on 08.11.2017.
 */
public class Word {
    public String s = "";
    private int id = -4;
    private String cz = "", la = "";
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
        this.s = s;

    }

    public void load() {
        int indexOfFirstSpace = s.indexOf(" ");
        this.id = Category.parseInt(s.substring(0, indexOfFirstSpace));
        String[] splited = s.substring(indexOfFirstSpace, s.length()).split("\\|");
        this.cz = splited[0];
        this.la = splited[1];
    }

    public String getCz() {
        if (cz.equals(""))
            load();
        return cz;
    }

    public String getLa() {
        if (la.equals(""))
            load();
        return la;
    }

    public int getId() {
        if (id == -4)
            load();
        return id;
    }
    public String toString() {
        return this.id + " " + this.cz + "|" + this.la;
    }
}
