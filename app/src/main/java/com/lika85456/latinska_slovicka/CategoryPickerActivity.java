package com.lika85456.latinska_slovicka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lika85456.latinska_slovicka.Resources.Category;

public class CategoryPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_picker);
        Category[] categories = Category.getCategories();
    }
}
