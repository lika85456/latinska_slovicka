package com.lika85456.latinska_slovicka.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lika85456.latinska_slovicka.Global;
import com.lika85456.latinska_slovicka.R;

public class MainActivity extends AppCompatActivity {

    public String categoriesInString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Global.main_activity_context = this;
        categoriesInString = getIntent().getStringExtra("array");




    }


    public void ProcvicovaniOnClick(View view)
    {
        Intent intent = new Intent(this, CategoryPickerActivity.class);
        intent.putExtra("goto",0);
        intent.putExtra("array", this.categoriesInString);

        startActivity(intent);



    }
    public void TestOnClick(View view)
    {
        Intent intent = new Intent(this, CategoryPickerActivity.class);
        intent.putExtra("goto",1);
        intent.putExtra("array", this.categoriesInString);
        startActivity(intent);


    }
    public void NastaveniOnClick(View view)
    {
        Intent intent = new Intent(this, NastaveniActivity.class);

        //intent.putExtra(COINS, coins);
        startActivity(intent);


    }
}
