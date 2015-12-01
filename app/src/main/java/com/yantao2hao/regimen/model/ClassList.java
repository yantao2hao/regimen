package com.yantao2hao.regimen.model;

import java.io.Serializable;
import java.util.List;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class ClassList implements Serializable {
    public List<ArticleClass> classesList ;

    public List<ArticleClass> getClassesList() {
        return classesList;
    }

    public void setClassesList(List<ArticleClass> classesList) {
        this.classesList = classesList;
    }
}
