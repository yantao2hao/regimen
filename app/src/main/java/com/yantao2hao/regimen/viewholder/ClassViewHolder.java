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
public class ClassViewHolder extends RecyclerView.ViewHolder {
    public View rootView ;
    public TextView title , des ;
    public ImageView img;
    public ClassViewHolder(View itemView) {
        super(itemView);
        rootView = itemView.findViewById(R.id.root_view) ;
        title = (TextView) itemView.findViewById(R.id.title_menu) ;
        des = (TextView) itemView.findViewById(R.id.des_menu);
        img = (ImageView) itemView.findViewById(R.id.menu_img);

    }
}
