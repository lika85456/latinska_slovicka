package com.lika85456.latinska_slovicka.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.lika85456.latinska_slovicka.CategorryArrayAdapter;
import com.lika85456.latinska_slovicka.R;
import com.lika85456.latinska_slovicka.Resources.Category;
import com.lika85456.latinska_slovicka.Resources.CategoryHandler;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoryPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_picker);
        final Context ctx = getApplicationContext();
        //Take from intent to what activity should be run
        final Intent main_intent = getIntent();

        //WordHandler.loadWords(this);

        //Some crap setting listView and its categories
        CategoryHandler categoryHandler = new CategoryHandler(getIntent().getStringExtra("category"));
        final ArrayList<Category> categories = new ArrayList<Category>(Arrays.asList(categoryHandler.categories));
        ArrayAdapter<Category> adapter = new CategorryArrayAdapter(this, 0, categories);
        ListView listView = (ListView) findViewById(R.id.categoryListView);
        listView.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.button_continue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if (main_intent.getIntExtra("goto", 0) == 0)
                    intent = new Intent(v.getContext(), CategoryActivity.class);
                else
                    intent = new Intent(v.getContext(), TestPickerActivity.class);
                String toPass = "";
                Boolean canContinue = false;
                for (int i = 0; i < categories.size(); i++) {
                    if (categories.get(i).selected == true) {
                        toPass += (categories.get(i)).toString();
                        canContinue = true;
                    }
                }
                if (canContinue == false) {
                    Toast.makeText(ctx, "Před pokračováním prosím zvolte kategorii.", Toast.LENGTH_LONG).show();

                    return;
                }

                intent.putExtra("category", toPass.substring(0, toPass.length() - 2));
                v.getContext().startActivity(intent);
            }
        });


    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
