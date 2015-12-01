package com.yantao2hao.regimen;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONArray;
import com.yantao2hao.regimen.Utils.ApiUtils;
import com.yantao2hao.regimen.adapter.ArticleAdapter;
import com.yantao2hao.regimen.adapter.ClassAdapter;
import com.yantao2hao.regimen.model.ArticleList;

import java.util.List;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class NewestFragment extends Fragment {
    public View rootView ;
    public RecyclerView recyclerview ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.arcitle_list_layout,container,false);
        recyclerview = (RecyclerView) rootView.findViewById(R.id.article_list_rc);
        return rootView;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadContentFromWeb();
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    public void loadContentFromWeb(){
        new LoadNewest().execute();
    }
    public void setUpUi(ArticleList articleList){
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerview.setAdapter(new ArticleAdapter(articleList, getActivity()));
    }
    public void setUpUi(JSONArray classList){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerview.setLayoutManager(gridLayoutManager);
        List<Object> classList1 = classList.subList(0,classList.size()-1) ;
        recyclerview.setAdapter(new ClassAdapter(getActivity() , classList1));
    }
    public class LoadNewest extends AsyncTask<String , Integer ,ArticleList> {
        @Override
        protected ArticleList doInBackground(String... params) {
            return ApiUtils.getNewest();
        }

        @Override
        protected void onPostExecute(ArticleList articleList) {
            super.onPostExecute(articleList);
            setUpUi(articleList);
        }
    }

    public class LoadAll extends AsyncTask<String , Integer ,ArticleList> {
        @Override
        protected ArticleList doInBackground(String... params) {
            return ApiUtils.getAll(1);
        }

        @Override
        protected void onPostExecute(ArticleList articleList) {
            super.onPostExecute(articleList);
            setUpUi(articleList);
        }
    }
    public class LoadClasses extends AsyncTask<String , Integer ,JSONArray> {
        @Override
        protected JSONArray doInBackground(String... params) {
            return ApiUtils.getClasses();
        }

        @Override
        protected void onPostExecute(JSONArray classList) {
            super.onPostExecute(classList);
            setUpUi(classList);
        }
    }
}
