package com.yantao2hao.regimen;

/**
 * author：yanyantao
 * Created on 2015/11/23.
 * 描述：
 */
public class AllFragment extends NewestFragment {
    @Override
    public void loadContentFromWeb() {
       new LoadAll().execute();
    }
}
