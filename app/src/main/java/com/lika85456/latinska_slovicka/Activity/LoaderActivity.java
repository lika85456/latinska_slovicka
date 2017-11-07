package com.lika85456.latinska_slovicka.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lika85456.latinska_slovicka.Global;
import com.lika85456.latinska_slovicka.R;
import com.lika85456.latinska_slovicka.Resources.CategoryManager;

public class LoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        Global.main_activity_context = this;

        CategoryManager categoryManager = new CategoryManager(this);
        categoryManager.update();
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("array", categoryManager.toString());
        startActivity(intent);
    }


}
