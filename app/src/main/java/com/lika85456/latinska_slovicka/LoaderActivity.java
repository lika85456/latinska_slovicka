package com.lika85456.latinska_slovicka;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lika85456.latinska_slovicka.Resources.Category;
import com.lika85456.latinska_slovicka.Resources.HttpHandler;
import com.lika85456.latinska_slovicka.Resources.StorageHandler;

public class LoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loader);
        Global.main_activity_context = this;
        if(NetworkUtils.isWifiConnected(this)) {
            HttpHandler http = new HttpHandler();
            String slovicka = http.getRequest("http://lika85456.4fan.cz/slovicka.txt", this);
            http = new HttpHandler();
            String category = http.getRequest("http://lika85456.4fan.cz/category.txt", this);
            //TODO aktualizuj slovicka
            Category[] categories = Category.loadFromResponses(slovicka,category);
            String categoriesInString = Category.arrayToString(categories);
            StorageHandler sHandler = new StorageHandler(this);
            sHandler.save("slovicka",categoriesInString);

        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
