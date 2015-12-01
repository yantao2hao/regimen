package com.yantao2hao.regimen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yantao2hao.regimen.R;
import com.yantao2hao.regimen.viewholder.LoadingMoreViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/6/25.
 */
public abstract class RecycleViewAdapter<T> extends RecyclerView.Adapter implements View.OnClickListener {
    private static final String TAG = "RecycleViewAdapter";
    protected List<T> list = new ArrayList<T>();
    protected static final int LOADING_VIEW_TYPE = -1;
    protected Context context;
    private LoadingMoreViewHolder loadingViewHolder;
    protected boolean isLoading = true;
    private OnItemClickListener listener;

    public boolean isLoading() {
        return isLoading;
    }

    public void setIsLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }

    public RecycleViewAdapter(Context context){
        this.context = context;
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == LOADING_VIEW_TYPE){
            View v = LayoutInflater.from(context).inflate(R.layout.view_load_more,parent,false);
            loadingViewHolder = new LoadingMoreViewHolder(v);
            return loadingViewHolder;
        }
        else{
            return createRecycleViewHolder(parent,viewType);
        }
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(!isLoading||(position<list.size())) {
            if(listener != null) {
                holder.itemView.setOnClickListener(this);
                holder.itemView.setTag(position);
            }
            bindRecycleViewHolder(holder, position);
        }
    }

    public T getItemObject(int postion){
        return list.get(postion);
    }

    /**
     * 写法{@code onCreateViewHolder}方法保持一致即可<br>
     * @see android.support.v7.widget.RecyclerView.Adapter#onCreateViewHolder(ViewGroup, int)
     * @param parent
     * @param ViewType
     * @return
     */
    protected abstract RecyclerView.ViewHolder createRecycleViewHolder(ViewGroup parent,int ViewType);

    /**
     * 写法{@code onBindViewHolder}方法保持一致即可<br>
     * @see android.support.v7.widget.RecyclerView.Adapter#onBindViewHolder(RecyclerView.ViewHolder holder, int position)
     * @param holder
     * @param position
     */
    protected abstract void bindRecycleViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public final int getItemCount() {
        if(isLoading)
            return list.size() + 1;
        else
            return list.size();
    }

    public final int getDataCount(){
        return list.size();
    }

    //protected abstract int getListViewItemCount();
    public void addItem(T data){
        int len = list.size();
        list.add(data);
        notifyItemRangeInserted(len,1);
    }

    public void addItems(List<T> dataList){
        if (dataList == null) {
            return;
        }
        int len = list.size();
        list.addAll(dataList);
        notifyItemRangeInserted(len, dataList.size());
    }

    public void removeItem(int index){
        removeItems(index, 1);
    }

    public void removeItems(int index,int size){
        notifyItemRangeRemoved(index, size);
        for(int i = 0;i < size;i ++){
            list.remove(index + i);
        }
    }

    @Override
    public final int getItemViewType(int position) {

        if (position == list.size())
            return LOADING_VIEW_TYPE;
        else {
            int type = getRecyclerViewItemType(position);
            if(type == LOADING_VIEW_TYPE){
                //Log.w(TAG,"View type duplicate with loading view!");
            }
            return type;
        }
    }

    public int getRecyclerViewItemType(int position){
        return super.getItemViewType(position);
    }

    public void setLoading(boolean loading){
        if(loading && loadingViewHolder != null) {
            loadingViewHolder.itemView.setVisibility(View.VISIBLE);
        }
        else{
            loadingViewHolder.itemView.setVisibility(View.GONE);
        }
    }

    public void deleteLoadingView(){
        isLoading = false;
        int len = list.size();
        notifyItemRemoved(len);
    }

    @Override
    public void onClick(View view){
        int position = (int) view.getTag();
        if(listener != null){
            listener.onItemClick(position,view);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        public void onItemClick(int position, View parent);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
