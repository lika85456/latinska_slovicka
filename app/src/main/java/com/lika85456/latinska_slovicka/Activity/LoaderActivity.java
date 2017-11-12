package com.lika85456.latinska_slovicka.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lika85456.latinska_slovicka.R;
import com.lika85456.latinska_slovicka.Resources.HttpHandler;
import com.lika85456.latinska_slovicka.Resources.Updater;

public class LoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);

        HttpHandler h = new HttpHandler();
        final Context ctx = this;
        //Update
        Boolean succes = new Updater(this) {
            public void run() {
                //Jump to MainActivity
                Intent intent = new Intent(ctx, MainActivity.class);
                startActivity(intent);
            }
        }.start();
        Intent intent = new Intent(ctx, MainActivity.class);
        startActivity(intent);

    }


}
