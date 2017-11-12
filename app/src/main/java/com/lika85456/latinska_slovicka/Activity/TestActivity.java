package com.lika85456.latinska_slovicka.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lika85456.latinska_slovicka.Global;
import com.lika85456.latinska_slovicka.R;
import com.lika85456.latinska_slovicka.Resources.Category;
import com.lika85456.latinska_slovicka.Resources.CategoryManager;
import com.lika85456.latinska_slovicka.Resources.Word;

import java.util.Random;

public class TestActivity extends AppCompatActivity {

    public Word[] words;
    public ImageView imageView;
    public TextView wordView;
    public Button b1;
    public Button b2;
    public Button b3;
    public Button b4;
    public Button buttonKategorie;

    //public TextView textViewSuccessAll;
    //public TextView textViewSuccessNow;
    public TextView textViewProgress;
    public TextView textViewLoader;
    public TextView textViewNLoader;
    public LinearLayout llGreen;
    public LinearLayout llNull;


    public int tryTotal;
    public int tryRight;
    public int TtryTotal;
    public int TtryRight;
    public int MAX_WORDS_IN_ROUND = 25;

    public byte rightWordID;
    public int lastWordIDD;


    public boolean processingClick = false;

    public static void shuffleArray(Word[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Word a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Global.main_activity_context = getApplicationContext();
        /*intent.putExtra("pocetSlovicek",pocetSlovicek.getText());
        intent.putExtra("timer",sTimer.isChecked());
        intent.putExtra("result",sResult.isChecked());
        */

        Category[] categoriesA = CategoryManager.makeWordIDArrayFromString(getIntent().getStringExtra("array"));
        //load them to public Word array
        int size = 0;
        for (int i = 0; i < categoriesA.length; i++) {
            size += categoriesA[i].getNumberOfWords();
        }
        words = new Word[size];
        int lastIndex = 0;
        for (int i = 0; i < categoriesA.length; i++) {
            System.arraycopy(categoriesA[i].words, 0, words, lastIndex, categoriesA[i].words.length);
            lastIndex += categoriesA[i].words.length;

        }
        shuffleArray(words);

        try {
            this.MAX_WORDS_IN_ROUND = Integer.parseInt(getIntent().getStringExtra("pocetSlovicek"));
        } catch (Exception e) {
            Log.e("ERROR", e.toString());
            this.MAX_WORDS_IN_ROUND = words.length;
        }

        //Views init
        imageView = (ImageView) findViewById(R.id.imageView);
        wordView = (TextView) findViewById(R.id.textView);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        buttonKategorie = (Button) findViewById(R.id.buttonKategorie);
        //textViewSuccessAll = (TextView) findViewById(R.id.textViewSuccessAll);
        //textViewSuccessNow = (TextView) findViewById(R.id.textViewSuccessNow);
        textViewLoader = (TextView) findViewById(R.id.textViewLoader);
        textViewNLoader = (TextView) findViewById(R.id.textViewNLoader);
        textViewProgress = (TextView) findViewById(R.id.textViewProgress);
        llGreen = (LinearLayout) findViewById(R.id.llGreen);
        llNull = (LinearLayout) findViewById(R.id.llNull);

        //Click events
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnClick(0, v);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnClick(1, v);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnClick(2, v);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOnClick(3, v);
            }
        });
        buttonKategorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(v.getContext(), CategoryPickerActivity.class);
                intent.putExtra("goto", 1);
                v.getContext().startActivity(intent);
            }
        });


        loadNextWord(-1);
    }

    public void btnOnClick(int id, View v) {
        if (processingClick == true)
            return;
        else
            processingClick = true;
        b1.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_wrong, null));
        b2.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_wrong, null));
        b3.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_wrong, null));
        b4.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_wrong, null));
        if (rightWordID == 0)
            b1.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_right, null));
        else if (rightWordID == 1)
            b2.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_right, null));
        else if (rightWordID == 2)
            b3.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_right, null));
        else if (rightWordID == 3)
            b4.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_right, null));
        tryTotal++;
        TtryTotal++;
        if (TtryTotal >= MAX_WORDS_IN_ROUND) {
            //reset round counter
            TtryRight = 0;
            TtryTotal = 1;
        }
        if (rightWordID == id) {
            tryRight++;
            TtryRight++;

        } else {
            //--
        }


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadNextWord(lastWordIDD);
            }
        }, 2000);
    }

    public void loadNextWord(int lastWordID) {

        /*if(lastWordID+1 == words.length)
        {
            Intent intent;
            intent = new Intent(this, TestResultActivity.class);
            startActivity(intent);
            return;
        }
        */
        processingClick = false;
        b1.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_normal, null));
        b2.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_normal, null));
        b3.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_normal, null));
        b4.setBackgroundDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.test_button_normal, null));
        Random r = new Random();

        Boolean isCz = r.nextBoolean();
        if (lastWordID + 1 == MAX_WORDS_IN_ROUND) {
            lastWordID = -1;
        }
        int id = lastWordID + 1;
        this.lastWordIDD = id;
        Word w = words[id];
        if (w.icon != null || w != null)
            imageView.setImageDrawable(w.icon);
        if (isCz)
            wordView.setText(w.la);
        else
            wordView.setText(w.cz);
        Word[] wordds = generate4(w);
        rightWordID = (byte) randomFromTo(0, 3);
        wordds[rightWordID] = w;
        if (!isCz) {
            //Load in latin
            b1.setText(wordds[0].la);
            b2.setText(wordds[1].la);
            b3.setText(wordds[2].la);
            b4.setText(wordds[3].la);
        } else {
            //Load in czech
            b1.setText(wordds[0].cz);
            b2.setText(wordds[1].cz);
            b3.setText(wordds[2].cz);
            b4.setText(wordds[3].cz);
        }
        float procentaT = 0;
        float procenta = 0;
        //Set statistics shit text


        if (TtryTotal != 0)
            procentaT = ((float) TtryRight / (float) TtryTotal);
        if (tryTotal != 0)
            procenta = ((float) tryRight / (float) tryTotal);


        //textViewSuccessNow.setText("Vaše úspěšnost v "+MAX_WORDS_IN_ROUND+" kolech: "+TtryRight+"/"+TtryTotal+" = "+(int)(procentaT*100f)+"%");
        //textViewSuccessAll.setText("Vaše celková úspěšnost: "+tryRight+"/"+tryTotal+" = "+(int)(procenta*100f)+"%");
        textViewLoader.setText((int) (procenta * 100) + "%");
        textViewNLoader.setText(tryRight + "/" + tryTotal);
        textViewProgress.setText((id + 1) + "/" + MAX_WORDS_IN_ROUND);

        LinearLayout.LayoutParams Green = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                procenta
        );
        LinearLayout.LayoutParams Null = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1f - procenta
        );
        Green.setMargins(0, 20, 0, 20);
        llGreen.setLayoutParams(Green);
        llNull.setLayoutParams(Null);

    }

    public Word[] generate4(Word SelectedWord) {
        Word[] newA = this.words.clone();
        Word[] ret = new Word[4];
        for (int i = 0; i < 4; i++) {
            int idd = randomFromTo(0, newA.length);
            Word temp = newA[idd];
            while (temp == SelectedWord || temp == null) {
                idd = randomFromTo(0, newA.length);
                temp = newA[idd];
            }

            ret[i] = temp;
            newA[idd] = null;

        }
        return ret;
    }

    public int randomFromTo(int minimum, int maximum) {
        return minimum + (int) (Math.random() * (maximum - minimum));
    }
}
