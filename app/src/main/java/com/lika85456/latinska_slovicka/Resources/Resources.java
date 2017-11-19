package com.lika85456.latinska_slovicka.Resources;

/**
 * Created by lika85456 on 08.11.2017.
 * Class managing with slovicka and category resources
 */
public class Resources {
    public String slovicka;
    public String category;

    /***
     * Makes Resources class again from s string
     *
     * @param s string made from toString method of this class
     */
    public Resources(String s) {
        String[] splited = s.split("€");
        this.slovicka = splited[0];
        this.category = splited[1];
    }

    public Resources() {

    }

    public String toString() {
        return (this.slovicka + "€" + this.category);
    }
}
