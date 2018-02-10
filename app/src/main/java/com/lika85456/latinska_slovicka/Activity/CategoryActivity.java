package com.lika85456.latinska_slovicka.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import com.lika85456.latinska_slovicka.Resources.WordHandler;

public class CategoryActivity extends AppCompatActivity {

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

        long milis = System.currentTimeMillis();
        String categoryToParse = getIntent().getStringExtra("category");
        final Category[] categories = Category.resourceToArray(categoryToParse);
        resources = Loader.getResources(this.getBaseContext());
        new WordHandler(resources);
        int size = 0;
        for (int i = 0; i < categories.length; i++) {
            size += categories[i].getWordCount();
        }
        words = new Word[size];
        int[] allRange = new int[size];
        Log.d("TIME", "Breakpoint 1:" + String.valueOf(System.currentTimeMillis() - milis));
        int index = 0;
        for (int i = 0; i < categories.length; i++) {
            int[] range = categories[i].getRange();
            for (int x = 0; x < range.length; x++) {
                allRange[index] = range[x];
                index += 1;
            }
        }
        words = WordHandler.getWordsFromRange(allRange);


        loadWord(words[word_id]);
        Log.d("TIME", "Total load time:" + String.valueOf(System.currentTimeMillis() - milis));

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
        imageView.setImageDrawable(DrawableGetter.getWordDrawable(w.getId(), this));
        latinView.setText(w.getLa());
        czechView.setText(w.getCz());
    }

}
