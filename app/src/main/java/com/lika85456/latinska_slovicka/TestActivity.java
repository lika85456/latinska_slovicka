package com.lika85456.latinska_slovicka;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.lika85456.latinska_slovicka.Resources.Category;
import com.lika85456.latinska_slovicka.Resources.Word;

import java.util.ArrayList;
import java.util.Random;

public class TestActivity extends AppCompatActivity {

    public Word[] words;
    public ImageView imageView;
    public TextView wordView;
    public Button b1;
    public Button b2;
    public Button b3;
    public Button b4;
    public Button button_back;
    public Button button_next;
    public byte rightWordID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //Get selected categories
        ArrayList<Category> categories = new ArrayList<Category>();
        String toParse = getIntent().getStringExtra("array");
        String[] toParseA = toParse.split("€");
        for(int i=0;i<toParseA.length;i++)
        {
            categories.add(new Category(toParseA[i]));
        }
        Category[] categoriesA = categories.toArray(new Category[0]);
        //load them to public Word array
        int size = 0;
        for(int i = 0;i<categoriesA.length;i++){
            size+=categoriesA[i].getNumberOfWords();
        }
        words = new Word[size];
        for(int i=0;i<categoriesA.length;i++)
        {
            System.arraycopy(categoriesA[i].words,0,words,0,categoriesA[i].words.length);
        }

        //Views init
        imageView = (ImageView)findViewById(R.id.imageView);
        wordView = (TextView) findViewById(R.id.textView);
        b1 = (Button)findViewById(R.id.button1);
        b2 = (Button)findViewById(R.id.button2);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        button_back = (Button)findViewById(R.id.button_back);
        button_next = (Button)findViewById(R.id.button_next);

        //Click events



        loadNextWord(-1);
    }

    public void loadNextWord(int lastWordID)
    {
        Random r = new Random();

        Boolean isCz = r.nextBoolean();

        int id = randomFromTo(0,words.length);
        while(id==lastWordID)
        {
            id = randomFromTo(0,words.length);
        }
        Word w = words[id];

        imageView.setImageDrawable(w.icon);
        if(isCz)
            wordView.setText(w.la);
        else
            wordView.setText(w.cz);
        Word[] wordds = generate4(w);
        rightWordID = (byte)randomFromTo(0,3);
        wordds[rightWordID] = w;
        if(!isCz)
        {
            //Load in latin
            b1.setText(wordds[0].la);
            b2.setText(wordds[1].la);
            b3.setText(wordds[2].la);
            b4.setText(wordds[3].la);
        }
        else
        {
            //Load in czech
            b1.setText(wordds[0].cz);
            b2.setText(wordds[1].cz);
            b3.setText(wordds[2].cz);
            b4.setText(wordds[3].cz);
        }
    }

    public Word[] generate4(Word SelectedWord)
    {
        Word[] newA = this.words.clone();
        Word[] ret = new Word[4];
        for(int i = 0;i<4;i++)
        {
            int idd = randomFromTo(0,newA.length);
            Word temp = newA[idd];
            while(temp==SelectedWord || temp==null)
            {
                idd = randomFromTo(0,newA.length);
                temp = newA[idd];
            }

            ret[i] = temp;
            newA[idd] = null;

        }
        return ret;
    }

    public int randomFromTo(int minimum,int maximum)
    {
        return minimum + (int)(Math.random() * (maximum - minimum));
    }
}
