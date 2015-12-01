package com.yantao2hao.regimen;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yantao2hao.regimen.adapter.MainViewPagerAdapter;

import static com.yantao2hao.regimen.R.id.id_tabs;

public class MainActivity extends AppCompatActivity {

    private ViewPager mainViewPager ;
    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getViews();
        initViews();
    }

    private void getViews(){
        mainViewPager = (ViewPager) findViewById(R.id.main_view_pager);
        tabLayout = (TabLayout) findViewById(id_tabs);
    }
    private void initViews(){
        mainViewPager.setAdapter(new MainViewPagerAdapter(getSupportFragmentManager()));
        mainViewPager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(mainViewPager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
