package com.lika85456.latinska_slovicka.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lika85456.latinska_slovicka.R;
import com.lika85456.latinska_slovicka.Resources.Category;
import com.lika85456.latinska_slovicka.Resources.DrawableGetter;
import com.lika85456.latinska_slovicka.Resources.Loader;
import com.lika85456.latinska_slovicka.Resources.Resources;
import com.lika85456.latinska_slovicka.Resources.Word;


public class PracticeActivity extends AppCompatActivity {

    public final int BACK = 0;
    public final int NEXT = 1;
    public int word_id = 0;
    public ImageView imageView;
    public TextView latinView;
    public TextView czechView;
    public Word[] words;
    public Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //Onclick events
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                idmove(BACK);
            }
        });
        Button button_next = (Button) findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                idmove(NEXT);
            }
        });


        //Views init
        imageView = (ImageView) findViewById(R.id.imageView);
        latinView = (TextView) findViewById(R.id.textView_latin);
        czechView = (TextView) findViewById(R.id.textView_czech);


        resources = Loader.getResources(this.getBaseContext());
        String categoryToParse = getIntent().getStringExtra("category");
        Category[] categories = resources.getCategories(categoryToParse);
        words = resources.getWordsFromRange(resources.getRangeFromCategories(categories));


        loadWord(words[word_id]);


    }

    public void idmove(int moveid) {
        if (moveid == BACK) {
            this.word_id--;
            if (word_id < 0) {
                word_id = words.length - 1;
            }
        }
        if (moveid == NEXT) {
            this.word_id++;
            if (word_id > words.length - 1) {
                this.word_id = 0;
            }
        }
        loadWord(words[word_id]);
    }

    public void loadWord(Word w) {
        imageView.setImageDrawable(DrawableGetter.getWordDrawable(w.getImageId(), this));
        latinView.setText(w.getLa());
        czechView.setText(w.getCz());
    }

}
