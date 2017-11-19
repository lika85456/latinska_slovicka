package com.lika85456.latinska_slovicka.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.lika85456.latinska_slovicka.R;

/**
 * author: lika85456
 * <p/>
 * Test settings
 */
public class TestPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_picker);


        Button btnStart = (Button) findViewById(R.id.buttonStart);
        final EditText pocetSlovicek = (EditText) findViewById(R.id.editPocetSlovicek);
        final Switch sTimer = (Switch) findViewById(R.id.switchTimer);
        final Switch sResult = (Switch) findViewById(R.id.switchResult);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                String pocetS = pocetSlovicek.getText().toString();
                if (pocetS.equals("") || pocetS == null) {
                    pocetS = "50";
                }
                intent.putExtra("pocetSlovicek", pocetS);
                intent.putExtra("timer", sTimer.isChecked());
                intent.putExtra("result", sResult.isChecked());
                String toParse = getIntent().getStringExtra("category");
                intent.putExtra("category", toParse);
                intent.putExtra("resources", getIntent().getStringExtra("resources"));
                startActivity(intent);
            }
        });
    }


}
