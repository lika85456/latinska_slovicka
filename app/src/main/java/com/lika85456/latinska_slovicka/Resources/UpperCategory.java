package com.lika85456.latinska_slovicka.Resources;

import android.view.View;
import android.widget.CheckBox;

import com.lika85456.latinska_slovicka.R;

import java.util.ArrayList;

/**
 * Created by lika85456 on 04.02.2018.
 */
public class UpperCategory {
    public String name;
    public ArrayList<Category> under_categories;
    public ArrayList<View> under_categories_views;
    public boolean hidden = true;

    public UpperCategory(String name) {
        under_categories = new ArrayList<>();
        under_categories_views = new ArrayList<>();
        this.name = name;
    }

    public void addCategory(Category name) {
        under_categories.add(name);
    }

    public String getSelectedCategories() {
        String toRet = "";
        for (int i = 0; i < under_categories_views.size(); i++) {
            CheckBox ck = (CheckBox) (under_categories_views.get(i).findViewById(R.id.checkBox2));
            if (ck.isChecked()) {
                toRet += under_categories.get(i).toString();
            }
        }
        return toRet;
    }

    public void checkAll(boolean check) {
        for (int i = 0; i < under_categories_views.size(); i++) {
            CheckBox ck = (CheckBox) (under_categories_views.get(i).findViewById(R.id.checkBox2));
            //TODO CHECK COLOR
            ck.setChecked(check);
        }
    }

    public void show() {
        hidden = false;
        for (int i = 0; i < under_categories_views.size(); i++) {
            under_categories_views.get(i).setVisibility(View.VISIBLE);
        }
    }

    public void hide() {
        hidden = true;
        for (int i = 0; i < under_categories_views.size(); i++) {
            under_categories_views.get(i).setVisibility(View.GONE);
        }
    }
}
