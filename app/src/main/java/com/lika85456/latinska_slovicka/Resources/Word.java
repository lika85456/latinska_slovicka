package com.lika85456.latinska_slovicka.Resources;

/**
 * Created by lika85456 on 08.11.2017.
 */
public class Word {
    public String raw = "";
    private int id = -1;
    private String cz = "", la = "";
    private int imageId = -1;
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
        this.raw = s;

    }

    public void load() {
        int indexOfFirstSpace = raw.indexOf(" ");
        this.id = Category.parseInt(raw.substring(0, indexOfFirstSpace));
        String[] splited = raw.substring(indexOfFirstSpace, raw.length()).split("\\|");
        this.cz = splited[0];
        this.la = splited[1];
        if (splited.length == 3)
            this.imageId = Integer.parseInt(splited[2]);
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
        if (id == -1)
            load();
        return id;
    }
    public String toString() {
        if (id == -1)
            load();
        return this.id + " " + this.cz + "|" + this.la;
    }

    public int getImageId() {
        if (id == -1)
            load();
        return imageId;
    }
}
