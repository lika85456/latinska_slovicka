package com.lika85456.latinska_slovicka;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lika85456 on 30.08.2017.
 */
public class CategoryView extends RelativeLayout {

    View rootView;
    TextView textView;
    ImageView imageView;


    public CategoryView(Context context) {
        super(context);
    }

    public CategoryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CategoryView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        textView = (TextView)findViewById(R.id.category_text_view);
        imageView = (ImageView)findViewById(R.id.category_image_view);
        rootView = inflate(context, R.layout.value_selector, this);

    }
}
