package com.yantao2hao.regimen.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.yantao2hao.regimen.ArticleDetailActivity;
import com.yantao2hao.regimen.R;
import com.yantao2hao.regimen.model.Article;
import com.yantao2hao.regimen.viewholder.ArticleViewHolder;

import java.util.Calendar;

/**
 * author：yanyantao
 * Created on 2015/11/25.
 * 描述：
 */
public class SearchResultListAdapter extends RecycleViewAdapter<Article> {
    //private ArticleList articleList ;
    private Context context ;
    public static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .cacheInMemory(true)//设置下载的图片是否缓存在内存中
            .cacheOnDisc(true)//设置下载的图片是否缓存在SD卡中
            .considerExifParams(true)  //是否考虑JPEG图像EXIF参数（旋转，翻转）
            .displayer(new FadeInBitmapDisplayer(100))//是否图片加载好后渐入的动画时间
            .build();//构建完成
    public SearchResultListAdapter(Context context) {
        super(context);
        this.context = context ;
    }

    @Override
    protected RecyclerView.ViewHolder createRecycleViewHolder(ViewGroup parent, int ViewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.article_item_layout,parent,false);
        return new ArticleViewHolder(root);
    }

    @Override
    protected void bindRecycleViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Article article = list.get(position);
        ArticleViewHolder viewHolder = (ArticleViewHolder) holder;
        viewHolder.title .setText(article.getTitle());
        viewHolder.time.setText(getTimeFromNow(article));
        ImageLoader.getInstance().displayImage(article.getImg(), viewHolder.articleImage, options, null);
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ArticleDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("article",article);
                intent.putExtra("article",bundle);
                context.startActivity(intent);
            }
        });
    }

    private String getTimeFromNow(Article article){
        Calendar now = Calendar.getInstance();
        Calendar time = article.getTime();
        int year = now.get(Calendar.YEAR) ;
        int month = now.get(Calendar.MONTH);
        int day = now.get(Calendar.DAY_OF_MONTH);
        if (year>time.get(Calendar.YEAR))
            return (year-time.get(Calendar.YEAR))+"年前";
        else if (month>time.get(Calendar.MONTH))
            return (month-time.get(Calendar.MONTH))+"个月前";
        else if (day>time.get(Calendar.DAY_OF_MONTH))
            return (year-time.get(Calendar.DAY_OF_MONTH))+"天前";
        else return "" ;
    }
}
