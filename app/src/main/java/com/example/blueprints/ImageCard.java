package com.example.blueprints;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ImageCard extends androidx.appcompat.widget.AppCompatImageView {
    public ImageCard(@NonNull Context context) {
        super(context);
    }

    public ImageCard(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    
    public ImageCard(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }
}
