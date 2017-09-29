package com.lika85456.latinska_slovicka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lika85456.latinska_slovicka.Resources.Category;
import com.lika85456.latinska_slovicka.Resources.Word;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    public int word_id=0;

    public ImageView imageView;
    public TextView latinView;
    public TextView czechView;

    public final int BACK = 0;
    public final int NEXT = 1;

    public Word[] words;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);


        ArrayList<Category> categories = new ArrayList<Category>();
        String toParse = getIntent().getStringExtra("array");
        String[] toParseA = toParse.split("€");
        for(int i=0;i<toParseA.length;i++)
        {
            categories.add(new Category(toParseA[i]));
        }
        Category[] categoriesA = categories.toArray(new Category[0]);
        int size = 0;
        for(int i = 0;i<categoriesA.length;i++){
            size+=categoriesA[i].getNumberOfWords();
        }
        words = new Word[size];
        for(int i=0;i<categoriesA.length;i++)
        {
            System.arraycopy(categoriesA[i].words,0,words,0,categoriesA[i].words.length);
        }

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
        imageView = (ImageView)findViewById(R.id.imageView);
        latinView = (TextView) findViewById(R.id.textView_latin);
        czechView = (TextView) findViewById(R.id.textView_czech);


        loadWord(words[word_id]);
    }

    public void idmove(int moveid)
    {
        if(moveid==BACK)
        {
            this.word_id--;
            if(word_id<0)
            {
                word_id = words.length-1;
            }
        }
        if(moveid==NEXT)
        {
            this.word_id++;
            if(word_id>words.length-1)
            {
                this.word_id = 0;
            }
        }
        loadWord(words[word_id]);
    }

    public void loadWord(Word w)
    {
        imageView.setImageDrawable(w.icon);
        latinView.setText(w.la);
        czechView.setText(w.cz);
    }
}
