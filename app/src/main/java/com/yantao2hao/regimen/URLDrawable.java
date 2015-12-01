package com.yantao2hao.regimen;

/**
 * author：yanyantao
 * Created on 2015/11/25.
 * 描述：
 */
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class URLDrawable extends BitmapDrawable {
    public Drawable drawable;
    @Override
    public void draw(Canvas canvas) {

        if (drawable != null) {
            drawable.draw(canvas);
        }
    }
}
