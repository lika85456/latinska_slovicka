package com.lika85456.latinska_slovicka.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lika85456.latinska_slovicka.Global;
import com.lika85456.latinska_slovicka.R;

public class NastaveniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nastaveni);
        Global.main_activity_context = this;
    }
}
