package com.yantao2hao.regimen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.yantao2hao.regimen.Utils.ApiUtils;
import com.yantao2hao.regimen.adapter.SearchResultListAdapter;
import com.yantao2hao.regimen.model.Article;
import com.yantao2hao.regimen.model.ArticleList;

import java.util.List;

/**
 * author：yanyantao
 * Created on 2015/11/25.
 * 描述：
 */
public class SearchResultActivity extends AppCompatActivity implements LazyLoadRecyclerView.OnLoadMore {

    private LazyLoadRecyclerView lazyRecyclerView ;
    private List<Article> articleList ;
    private String keyWorlds ;
    private SearchResultListAdapter adapter ;
    private int page = 1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lazy_load_list_layout);
        getViews();
        initViews();
    }
    private void getViews(){
        lazyRecyclerView = (LazyLoadRecyclerView) findViewById(R.id.article_list_llrv);
    }
    private void initViews(){
        keyWorlds = getIntent().getStringExtra("keyWorlds");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(keyWorlds);
        adapter = new SearchResultListAdapter(this);
        lazyRecyclerView.setAdapter(adapter);
        lazyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        lazyRecyclerView.setOnLoadMore(this);
    }

    @Override
    public void loadMore() {
        new LoadArticles().execute();
    }

    private boolean isAll(ArticleList articleList){

        long total = articleList.getTotal() ;

        long get = articleList.getTngou().size()*page;
        if (total>get)
            return false ;
        return true ;
    }
    private void setUpUi(ArticleList articleList){

        adapter.addItems(articleList.getTngou());
        if (isAll(articleList))
            lazyRecyclerView.setLoadAllComplete(true);
        else page++;
    }
    public class LoadArticles extends AsyncTask<String , Integer ,ArticleList> {
        @Override
        protected ArticleList doInBackground(String... params) {
            return ApiUtils.search(keyWorlds, page);
        }

        @Override
        protected void onPostExecute(ArticleList classList) {
            super.onPostExecute(classList);
            setUpUi(classList);
        }
    }
}
