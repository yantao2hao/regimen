package com.yantao2hao.regimen;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.yantao2hao.regimen.adapter.RecycleViewAdapter;

/**
 * 1.如果RecyclerView没有填充满，则继续加载直到填充满数据或没有了新的数据
 * 2.如果RecyclerView填充满，RecyclerView滚动到底部继续加载直到没有新的数据
 * Created by Administrator on 2015/9/14.
 */
public class LazyLoadRecyclerView extends RecyclerView {

    protected boolean loadAllComplete = false;
    protected OnLoadMore loadMoreListener;
    protected boolean isFirstLayout = true;
    protected RecycleViewAdapter adapter;

    protected OnScrollListener listener = new RecyclerView.OnScrollListener(){
        public void onScrollStateChanged(RecyclerView recyclerView, int newState){
            if(newState != SCROLL_STATE_IDLE){
                if(!loadAllComplete && !isFirstLayout) {
                    if (!isFillFull()) {
                        adapter.setLoading(false);
                    } else {
                        adapter.setLoading(true);
                    }
                }
                else{
                    adapter.setLoading(false);
                }
            }
        }

        public void onScrolled(RecyclerView recyclerView, int dx, int dy){
            if(!loadAllComplete && !isFirstLayout){
                if(!isFillFull()){
                    load();
                }
                else{
                    if(isSrcolledBottom()){
                        load();
                    }
                }
            }
        }

    };

    @Override
    public void setAdapter(Adapter adapter) {
        this.adapter = (RecycleViewAdapter) adapter;
        super.setAdapter(adapter);
    }

    public LazyLoadRecyclerView(Context context) {
        super(context);
        addOnScrollListener(listener);
    }

    public LazyLoadRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        addOnScrollListener(listener);
    }

    public void setOnLoadMore(OnLoadMore loadMoreListener){
        this.loadMoreListener = loadMoreListener;
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(isFirstLayout){
            load();
        }
        isFirstLayout = false;
    }

    /**
     * RecyclerView 有没有填充满
     * @return
     */
    protected boolean isFillFull(){
        if(adapter.getItemCount() <= 1){
            return false;
        }
        else {
            int lastPosition = adapter.getItemCount() - 1;
            LinearLayoutManager layoutManager = (LinearLayoutManager)getLayoutManager();
            View lastView = layoutManager.findViewByPosition(lastPosition);
            if(lastView != null){
                if(lastView.getHeight() + lastView.getY() >= getHeight()){
                    return true;
                }
                else{
                    return false;
                }
            }
            else{
                return true;
            }
        }
    }

    protected boolean isSrcolledBottom(){
        LinearLayoutManager layoutManager = (LinearLayoutManager)getLayoutManager();
        int lastPosition = layoutManager.findLastVisibleItemPosition();
        if(lastPosition <= 0){
            return false;
        }
        //View lastView = layoutManager.findViewByPosition(lastPosition);
        if(lastPosition<getAdapter().getItemCount()-1){
            return false;
        }
        else {
            return true;
        }
    }

    public interface OnLoadMore {
        public void loadMore();
    }

    /**
     * 设置一批数据load完成
     * @param complete
     */
    public void setLoadComplete(boolean complete){
        if(complete){
            adapter.setLoading(false);
        }
    }

    public void setLoadAllComplete(boolean loadAllComplete){
        this.loadAllComplete = loadAllComplete;
        if(loadAllComplete){
            adapter.deleteLoadingView();
        }
    }

    private void load(){
        if(loadMoreListener != null){
            loadMoreListener.loadMore();
        }
    }

}
