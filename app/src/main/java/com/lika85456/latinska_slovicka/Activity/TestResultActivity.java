package com.lika85456.latinska_slovicka.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lika85456.latinska_slovicka.Global;
import com.lika85456.latinska_slovicka.R;

public class TestResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        Global.main_activity_context = getApplicationContext();
    }
}
