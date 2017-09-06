package com.lika85456.latinska_slovicka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lika85456.latinska_slovicka.Resources.Category;

import java.util.ArrayList;

public class CategoryPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_picker);
        ArrayList<Category> categories = Category.getCategories(this);
        ArrayAdapter<Category> adapter = new CategorryArrayAdapter(this, 0, categories);
        ListView listView = (ListView) findViewById(R.id.categoryListView);
        listView.setAdapter(adapter);
    }
}
