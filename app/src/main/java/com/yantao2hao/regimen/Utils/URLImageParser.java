package com.yantao2hao.regimen.Utils;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html.ImageGetter;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.yantao2hao.regimen.R;
import com.yantao2hao.regimen.URLDrawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLImageParser implements ImageGetter {
    private Context c;
    private TextView tv_image;
    private Drawable mDefaultDrawable;
    private DisplayMetrics displayMatrix;
    public URLImageParser(TextView t, Context c ,DisplayMetrics displayMatrix) {
        this.tv_image = t;
        this.c = c;
        this.displayMatrix = displayMatrix ;
        mDefaultDrawable = c.getResources().getDrawable(R.drawable.p12);
    }

    @Override
    public Drawable getDrawable(String source) {
        // TODO Auto-generated method stub
        URLDrawable urlDrawable = new URLDrawable();
        try {
            urlDrawable.drawable = mDefaultDrawable;
            this.tv_image.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*
         * urlDrawable.setBounds(0, 0, 0 + mDefaultDrawable.getIntrinsicWidth(),
         * mDefaultDrawable.getIntrinsicHeight());
         */
        ImageGetterAsyncTask asyncTask = new ImageGetterAsyncTask(urlDrawable);
        asyncTask.execute(source);
        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable> {
        URLDrawable urlDrawable;

        public ImageGetterAsyncTask(URLDrawable d) {
            this.urlDrawable = d;
        }

        @Override
        protected void onPostExecute(Drawable result) {
            if (result != null) {
                urlDrawable.drawable = result;
                URLImageParser.this.tv_image.invalidate();
            }
        }

        @Override
        protected void onPreExecute() {
            urlDrawable.setBounds(0, 0,
                    0 + mDefaultDrawable.getIntrinsicWidth(),
                    0 + mDefaultDrawable.getIntrinsicHeight());
            urlDrawable.drawable = mDefaultDrawable;
            URLImageParser.this.tv_image.invalidate();
            super.onPreExecute();
        }

        @Override
        protected Drawable doInBackground(String... params) {
            // TODO Auto-generated method stub
            String source = params[0];// 图片URL
            return fetchDrawable(source);
        }

        // 获取URL的Drawable对象
        public Drawable fetchDrawable(String urlString) {
            BitmapDrawable bitmap = null;
            Drawable drawable = null;
            try {
                InputStream is = fetch(urlString);
                bitmap = (BitmapDrawable) BitmapDrawable.createFromStream(is,"src");
                drawable = bitmap;
/*                if(bitmap.getBitmap().getWidth()>displayMatrix.widthPixels||bitmap.getBitmap().getHeight()>displayMatrix.heightPixels)
                    //进行等比例缩放程序
                    drawable.setBounds(0, 0, displayMatrix.widthPixels, ((int)(displayMatrix.widthPixels*bitmap.getBitmap().getHeight()/bitmap.getBitmap().getWidth())));
                else*/
                    drawable.setBounds(0,0,bitmap.getBitmap().getWidth(),bitmap.getBitmap().getHeight());
            } catch (Exception e) {
                return null;
            }
            return drawable;
        }

        private InputStream fetch(String urlString)
                throws MalformedURLException, IOException {
            URL url = new URL(urlString);
            URLConnection connection = url.openConnection();
            return connection.getInputStream();
        }
    }

    private void setDisplayMetrics(DisplayMetrics displayMatrix){

        this.displayMatrix = displayMatrix ;
    }
}