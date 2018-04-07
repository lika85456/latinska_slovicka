package com.lika85456.latinska_slovicka.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lika85456.latinska_slovicka.R;
import com.lika85456.latinska_slovicka.Resources.Loader;

public class MainActivity extends AppCompatActivity {
    public Intent intent;
    public boolean canProceed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Loader.loadResources(this.getBaseContext());
        intent = new Intent(this, CategoryPickerActivity.class);

    }


    public void ProcvicovaniOnClick(View view) {
        intent.putExtra("goto", 0);
        startActivity(intent);


    }

    public void TestOnClick(View view) {
        intent.putExtra("goto", 1);
        startActivity(intent);


    }

    public void NastaveniOnClick(View view) {
        //intent.putExtra(COINS, coins);
        startActivity(intent);


    }
}
