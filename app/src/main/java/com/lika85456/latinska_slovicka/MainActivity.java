package com.lika85456.latinska_slovicka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lika85456.latinska_slovicka.Resources.Word;
import com.lika85456.latinska_slovicka.Resources.WordLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Global.context = this;
        Word[] w = WordLoader.load("zviratka");
    }
}
