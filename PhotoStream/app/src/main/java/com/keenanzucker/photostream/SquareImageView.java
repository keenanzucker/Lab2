package com.keenanzucker.photostream;

/**
 * Created by keenan on 10/1/15.
 * Custom ImageView that makes images nice square images to load into gridview for viewing pleasure.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SquareImageView extends ImageView {

    public SquareImageView(Context context) {
        super(context);

    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}