package com.example.iolo.satellite;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.iolo.satellite.view.ArcMenu;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private ListView mListView;
    private List<String> mDatas;
    private ArcMenu mArcMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initDate();
        initView();

        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas));

        initEvent();
    }
    //定义事件
    private void initEvent() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }
            //滑动事件
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //判断控件的状态
                if(mArcMenu.isOpen()){
                    mArcMenu.toggleMenu(600);
                }
            }
        });

        mArcMenu.setOnMenuItemListener(new ArcMenu.OnMenuItemClickListener() {
            @Override
            public void onClick(View view, int pos) {
                Toast.makeText(MainActivity.this,pos+":"+view.getTag(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 建立数据
     */
    private void initDate() {
        mDatas = new ArrayList<String>();
        for(int i = 'A';i < 'Z';i++){
            mDatas.add((char)i + "");
        }

    }

    /**
     * 加载布局
     */
    private void initView() {
        mListView = (ListView) findViewById(R.id.id_listview);
        mArcMenu  = (ArcMenu) findViewById(R.id.id_menu);
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
