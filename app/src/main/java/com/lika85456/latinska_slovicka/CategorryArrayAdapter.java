package com.lika85456.latinska_slovicka;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lika85456.latinska_slovicka.Resources.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lika85456 on 06.09.2017.
 */
class CategorryArrayAdapter extends ArrayAdapter<Category> {
    private final Context context;
    private List<Category> CategoryProperties;

    //constructor, call on creation
    public CategorryArrayAdapter(Context context, int resource, ArrayList<Category> objects) {
        super(context, resource, objects);

        this.context = context;
        this.CategoryProperties = objects;
    }

    //called when rendering the list
    public View getView(final int position, View convertView, ViewGroup parent) {

        //get the property we are displaying
        final Category property = CategoryProperties.get(position);

        //get the inflater and inflate the XML layout for each item
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category, null);

        TextView wordCountText = (TextView) view.findViewById(R.id.wordCount_text);
        TextView categoryNameText = (TextView) view.findViewById(R.id.categoryName_text);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);

        wordCountText.setText(String.valueOf(property.getNumberOfWords()));
        categoryNameText.setText(property.name);

        //get the image associated with this property
        imageView.setImageDrawable(property.image);
        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                property.selected = !property.selected;
                LinearLayout ln = (LinearLayout) v.findViewById(R.id.main_lin_layout);
                if(property.selected==true)
                ln.setBackgroundColor(0xFF0F406A);
                else
                ln.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), R.color.c_background, null));

            }
        });
        return view;
    }
    public Category getItem(int position){
        return CategoryProperties.get(position);
    }
}