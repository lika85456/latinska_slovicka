package com.lika85456.latinska_slovicka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lika85456.latinska_slovicka.Resources.HttpHandler;

public class MainActivity extends AppCompatActivity {

    public int coins = 0;
    public String nickname = "Guess";

    //TODO udělej hezký pozadí pro čudlíky a pro top-bar (prolínání mezi světle modrou a tmavě modrou třeba?)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Todo load coins and nickname if its set
        Global.main_activity_context = this;
        HttpHandler.getRequest("http://lika85456.4fan.cz/slovicka.txt",this);


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
