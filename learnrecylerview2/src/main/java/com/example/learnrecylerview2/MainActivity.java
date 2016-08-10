package com.example.learnrecylerview2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private LinearLayoutManager layoutManager;
//    mdata设置为列表型数据，类型为ItemData
    private List<ItemData> mdata;

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

    }
//  初始化数据
    private void initdata() {
        mdata=new ArrayList<ItemData>();
        for (int i=0;i<40;i++){
            mdata.add(new ItemData("标题"+i,"内容"+i));
        }
    }
//  初始化
    private void init() {
        myAdapter=new MyAdapter(MainActivity.this,mdata);
        recyclerView= (RecyclerView) findViewById(R.id.rv);
        layoutManager=new LinearLayoutManager(this);
    }

}
