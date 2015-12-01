package com.yantao2hao.regimen.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.yantao2hao.regimen.R;

/**
 * Created by Administrator on 2015/9/15.
 */
public class LoadingMoreViewHolder extends RecyclerView.ViewHolder {
    public ProgressBar progressBar;
    public LoadingMoreViewHolder(View itemView) {
        super(itemView);
        progressBar = (ProgressBar) itemView.findViewById(R.id.pb_load_more);
    }
}
