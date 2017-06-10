package com.lika85456.latinska_slovicka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String COINS = "com.lika85456.latinska_slovicka.COINS";
    public static final String NICKNAME = "com.lika85456.latinska_slovicka.NICKNAME";
    public int coins = 0;
    public String nickname = "Guess";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Global.main_activity_context = this;
        //Todo load coins and nickname if its set

    }

    public void ProcvicovaniOnClick(View view)
    {
        Intent intent = new Intent(this, ProcvicovaniActivity.class);

        intent.putExtra(COINS, coins);
        intent.putExtra(NICKNAME,nickname);
        startActivity(intent);


    }
}
