package com.yantao2hao.regimen.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yantao2hao.regimen.R;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder {
    public View rootView ;
    public ImageView articleImage ;
    public TextView title ;
    public TextView time ;
    public ArticleViewHolder(View itemView) {
        super(itemView);
        this.rootView = itemView.findViewById(R.id.article_root_rl);
        title = (TextView) itemView.findViewById(R.id.article_title);
        time = (TextView) itemView.findViewById(R.id.article_time);
        articleImage = (ImageView) itemView.findViewById(R.id.article_img);
    }
}
