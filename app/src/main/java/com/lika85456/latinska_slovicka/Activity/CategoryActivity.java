package com.lika85456.latinska_slovicka.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lika85456.latinska_slovicka.R;
import com.lika85456.latinska_slovicka.Resources.CategoryHandler;
import com.lika85456.latinska_slovicka.Resources.DrawableGetter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        String toParse = getIntent().getStringExtra("category");
        CategoryHandler categoryHandler = new CategoryHandler(toParse);

        WordHandler.loadWords(this);

        int size = 0;
        for (int i = 0; i < categoryHandler.categories.length; i++) {
            size += categoryHandler.categories[i].getWordCount();
        }
        words = new Word[size];
        int[] allRange = new int[size];

        int lastIndex = 0;
        for (int i = 0; i < categoryHandler.categories.length; i++) {
            System.arraycopy(categoryHandler.categories[i].range, 0, allRange, lastIndex, categoryHandler.categories[i].range.length);
            lastIndex += categoryHandler.categories[i].range.length;
        }
        words = WordHandler.getWordsFromRange(allRange);

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
        imageView.setImageDrawable(DrawableGetter.getWordDrawable(w.id, this));
        latinView.setText(w.la);
        czechView.setText(w.cz);
    }
}
