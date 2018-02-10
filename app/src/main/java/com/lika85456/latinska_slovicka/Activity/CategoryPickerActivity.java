package com.lika85456.latinska_slovicka.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lika85456.latinska_slovicka.R;
import com.lika85456.latinska_slovicka.Resources.Category;
import com.lika85456.latinska_slovicka.Resources.Loader;
import com.lika85456.latinska_slovicka.Resources.Resources;
import com.lika85456.latinska_slovicka.Resources.UpperCategory;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoryPickerActivity extends AppCompatActivity {
    private static ArrayList<Category> categories;
    private Resources resources;
    private ArrayList<UpperCategory> upperCategories;
    private Intent intent;

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_picker);


        final Context ctx = getApplicationContext();
        //Take from intent to what activity should be run
        final Intent main_intent = getIntent();


        //Some crap setting listView and its categories
        resources = Loader.getResources(this.getBaseContext());

        Category[] cArray = Category.resourceToArray(resources.category);

        categories = new ArrayList<Category>(Arrays.asList(cArray));

        LinearLayout scrollView = (LinearLayout) findViewById(R.id.scrollLayout);

        upperCategories = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            String temp[] = category.name.split("\\s+");
            int index = temp.length;
            boolean keep = true;
            for (int ii = 0; ii < temp.length; ii++) {
                if (isNumeric(temp[ii])) {
                    index = ii;
                } else if (temp[ii].contains("-") || temp[ii].contains("|")) {
                    keep = false;
                    index = ii;
                    break;
                }
            }
            String upperName = "";
            for (int ii = 0; ii < index; ii++) {
                upperName += temp[ii] + " ";
            }
            upperName = upperName.substring(0, upperName.length() - 1);
            if (keep == false) {
                category.name = "";
                for (int xi = index + 1; xi < temp.length; xi++) {
                    category.name += temp[xi] + " ";
                }
            }
            boolean shouldAdd = true;
            for (int ii = 0; ii < upperCategories.size(); ii++) {
                if (upperName.equals(upperCategories.get(ii).name)) {
                    upperCategories.get(ii).under_categories.add(category);
                    shouldAdd = false;
                    break;
                }
            }

            if (shouldAdd) {
                UpperCategory uc = new UpperCategory(upperName);
                uc.addCategory(category);
                upperCategories.add(uc);
            }
        }

        for (int i = 0; i < upperCategories.size(); i++) {
            final UpperCategory category = upperCategories.get(i);
            LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View v = vi.inflate(R.layout.upper_category, null);
            TextView upperCategoryName = (TextView) v.findViewById(R.id.category_name);
            upperCategoryName.setText(category.name);
            LinearLayout ln = (LinearLayout) (v.findViewById(R.id.category_main_row));

            //ImageView pointer = (ImageView)v.findViewById(R.id.imageView2);

            ln.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (category.hidden == false) {
                        category.hide();
                    } else
                        category.show();

                }
            });

            final CheckBox checkBox = (CheckBox) v.findViewById(R.id.checkBox);
            checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //TODO OZNAC/ODZNAC
                    category.checkAll(checkBox.isChecked());
                }
            });

            for (int ii = 0; ii < category.under_categories.size(); ii++) {
                View underCategoryView = vi.inflate(R.layout.category, null);
                TextView underCategoryName = (TextView) underCategoryView.findViewById(R.id.under_category_name);
                TextView underCategoryPocet = (TextView) underCategoryView.findViewById(R.id.numberOfWordsTextView);
                underCategoryPocet.setText("Slovíček: " + category.under_categories.get(ii).getWordCount());
                underCategoryName.setText(category.under_categories.get(ii).name);
                LinearLayout temp = (LinearLayout) v.findViewById(R.id.main_upper);
                underCategoryView.setVisibility(View.GONE);

                final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                params.topMargin = 10;
                params.leftMargin = 7;
                params.rightMargin = 7;
                underCategoryView.setLayoutParams(params);

                temp.addView(underCategoryView);

                category.under_categories_views.add(underCategoryView);
            }

            final LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.topMargin = 10;
            params.leftMargin = 7;
            params.rightMargin = 7;
            v.setLayoutParams(params);
            scrollView.addView(v);
        }


        Button btn = (Button) findViewById(R.id.button_continue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (main_intent.getIntExtra("goto", 0) == 0)
                    intent = new Intent(v.getContext(), CategoryActivity.class);
                else
                    intent = new Intent(v.getContext(), TestPickerActivity.class);
                String toPass = "";
                Boolean canContinue = false;

                for (int i = 0; i < upperCategories.size(); i++) {
                    UpperCategory tempC = upperCategories.get(i);
                    for (int x = 0; x < tempC.under_categories_views.size(); x++) {
                        CheckBox c = (CheckBox) (tempC.under_categories_views.get(x).findViewById(R.id.checkBox2));
                        if (c.isChecked() == true) {
                            toPass += (tempC.under_categories.get(x)).toString();
                        canContinue = true;
                    }
                    }

                }
                if (canContinue == false) {
                    Toast.makeText(ctx, "Před pokračováním prosím zvolte kategorii.", Toast.LENGTH_LONG).show();

                    return;
                }

                intent.putExtra("category", toPass.substring(0, toPass.length() - 1));
                v.getContext().startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("resources", resources.toString());
        startActivity(intent);
    }

    private ArrayList<View> getAllChildren(View v) {

        if (!(v instanceof ViewGroup)) {
            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            return viewArrayList;
        }

        ArrayList<View> result = new ArrayList<View>();

        ViewGroup vg = (ViewGroup) v;
        for (int i = 0; i < vg.getChildCount(); i++) {

            View child = vg.getChildAt(i);

            ArrayList<View> viewArrayList = new ArrayList<View>();
            viewArrayList.add(v);
            viewArrayList.addAll(getAllChildren(child));

            result.addAll(viewArrayList);
        }
        return result;
    }

}
