package com.yantao2hao.regimen.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSONObject;
import com.yantao2hao.regimen.R;
import com.yantao2hao.regimen.SearchResultActivity;
import com.yantao2hao.regimen.viewholder.ClassViewHolder;

import java.util.List;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class ClassAdapter extends RecyclerView.Adapter {
    private Context context ;
    private List<Object> classList ;
    public ClassAdapter(Context context, List<Object> classList) {
        this.context = context;
        this.classList = classList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.class_item_layout,parent,false);
        return new ClassViewHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        ClassViewHolder viewHolder = (ClassViewHolder) holder;
        viewHolder.title.setText(((JSONObject)classList.get(position)).get("title").toString());

        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String keywords = (String) ((JSONObject)classList.get(position)).get("keywords");
                Intent intent = new Intent();
                intent.setClass(context , SearchResultActivity.class);
                intent.putExtra("keyWorlds" ,keywords);
                context.startActivity(intent);
            }
        });
            switch (((JSONObject) classList.get(position)).get("id").toString()){
                case "1":
                    viewHolder.img.setImageResource(R.drawable.p1);
                    break;
                case "2":
                    viewHolder.img.setImageResource(R.drawable.p2);
                    break;
                case "3":
                    viewHolder.img.setImageResource(R.drawable.p3);
                    break;
                case "4":
                    viewHolder.img.setImageResource(R.drawable.p4);
                    break;
                case "5":
                    viewHolder.img.setImageResource(R.drawable.p5);
                    break;
                case "6":
                    viewHolder.img.setImageResource(R.drawable.p6);
                    break;
                case "7":
                    viewHolder.img.setImageResource(R.drawable.p7);
                    break;
                case "8":
                    viewHolder.img.setImageResource(R.drawable.p8);
                    break;
                case "10":
                    viewHolder.img.setImageResource(R.drawable.p10);
                    break;
                case "11":
                    viewHolder.img.setImageResource(R.drawable.p11);
                    break;
                case "12":
                    viewHolder.img.setImageResource(R.drawable.p12);
                    break;
                case "13":
                    viewHolder.img.setImageResource(R.drawable.p13);
                    break;
            }

    }

    @Override
    public int getItemCount() {
        if (classList == null)
            return 0;
        return classList.size();
    }
}
