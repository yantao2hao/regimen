package com.yantao2hao.regimen.model;

import java.io.Serializable;
import java.util.List;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class ArticleList implements Serializable {

    public long total ;
    public List<Article> tngou ;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<Article> getTngou() {
        return tngou;
    }

    public void setTngou(List<Article> tngou) {
        this.tngou = tngou;
    }
}
