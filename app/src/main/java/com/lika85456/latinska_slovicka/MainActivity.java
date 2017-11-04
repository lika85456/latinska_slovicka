package com.lika85456.latinska_slovicka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public int coins = 0;
    public String nickname = "Guess";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Global.main_activity_context = this;



    }


    public void ProcvicovaniOnClick(View view)
    {
        Intent intent = new Intent(this, CategoryPickerActivity.class);
        intent.putExtra("goto",0);


        startActivity(intent);



    }
    public void TestOnClick(View view)
    {
        Intent intent = new Intent(this, CategoryPickerActivity.class);
        intent.putExtra("goto",1);
        startActivity(intent);


    }
    public void NastaveniOnClick(View view)
    {
        Intent intent = new Intent(this, NastaveniActivity.class);

        //intent.putExtra(COINS, coins);
        startActivity(intent);


    }
}
