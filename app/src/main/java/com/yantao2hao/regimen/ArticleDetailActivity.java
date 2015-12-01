package com.yantao2hao.regimen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.yantao2hao.regimen.Utils.ApiUtils;
import com.yantao2hao.regimen.Utils.URLImageParser;
import com.yantao2hao.regimen.model.Article;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class ArticleDetailActivity extends AppCompatActivity {

    private Article article ;
    private TextView title , content ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_detail);
        Bundle bundle = getIntent().getBundleExtra("article");
        article = (Article) bundle.get("article");
        setUpViews();
    }

    private void setUpViews(){

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(article.getTitle());
        title = (TextView) findViewById(R.id.article_detail_title);
        content = (TextView) findViewById(R.id.article_detail_content);
        if (article==null)
            return;
        title.setText(article.getTitle());
        new LoadDetail().execute(article.getId());
    }
    private void setUpUi(final Article article){

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        URLImageParser imageGetter = new URLImageParser(content ,this,displayMetrics);
        content.setText(Html.fromHtml(article.getMessage(),imageGetter,null));
    }
    public class LoadDetail extends AsyncTask<Long , Integer ,Article> {
        @Override
        protected Article doInBackground(Long... params) {
            return ApiUtils.getDetail(params[0]);
        }

        @Override
        protected void onPostExecute(Article article) {
            super.onPostExecute(article);
            setUpUi(article);
        }
    }
}
