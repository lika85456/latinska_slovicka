package com.lika85456.latinska_slovicka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

public class CategoryPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_picker);
        ViewGroup layout = (ViewGroup) findViewById(R.id.linearLayout);
        CategoryView cv = new CategoryView(this);
        layout.addView(cv);

    }
}
