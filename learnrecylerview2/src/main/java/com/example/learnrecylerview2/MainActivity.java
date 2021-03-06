package com.example.learnrecylerview2;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.color.*;
import static android.R.color.holo_blue_dark;
import static android.R.color.holo_blue_light;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private LinearLayoutManager layoutManager;
//    mdata设置为列表型数据，类型为ItemData
    private List<ItemData> mdata;
    private FloatingActionButton fab,fab1;
    private int count=40;
    private ItemTouchHelper itemTouchHelper;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initdata();
        init();
        //设置adapter
        recyclerView.setAdapter(myAdapter);
        //设置item增加，移除动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //布局方式设置为垂直那个布局
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        itemTouchHelper.attachToRecyclerView(recyclerView);
        //为适配器中的每一项设置点击事件
        myAdapter.setOnItemClickListener(new MyAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(MainActivity.this,
                        "onClick事件       您点击了第："+position+"个Item",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                Toast.makeText(MainActivity.this,
                        "onLongClick事件       您点击了第："+position+"个Item",Toast.LENGTH_SHORT).show();

            }
        });
        fab1.setOnClickListener(this);

        fab.setOnClickListener(this);
//      设置下拉刷新
        swipeRefreshLayout.setOnRefreshListener(this);
//      设置刷新变换演示
        swipeRefreshLayout.setColorScheme(holo_blue_bright,
                holo_green_light,
                holo_orange_light,
                holo_red_light);

    }





    //  初始化数据
    private void initdata() {
        mdata=new ArrayList<ItemData>();
        for (int i=1;i<count;i++){
            mdata.add(new ItemData("标题"+i,"内容"+i));
        }
    }
//  初始化
    private void init() {
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        myAdapter=new MyAdapter(MainActivity.this,mdata);
        recyclerView= (RecyclerView) findViewById(R.id.rv);
        layoutManager=new LinearLayoutManager(this);
        ItemTouchHelper.Callback callback=new SimpleItemTouchHelperCallback(myAdapter);
        itemTouchHelper=new ItemTouchHelper(callback);
        swipeRefreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_container);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fab:
                myAdapter.addData(1);
                break;
            case R.id.fab1:
                myAdapter.removeData(0);
                break;
        }
    }
    /*刷新事件处理*/
    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);

    }
}
