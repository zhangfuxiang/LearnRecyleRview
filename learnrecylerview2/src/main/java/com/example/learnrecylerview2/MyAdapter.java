package com.example.learnrecylerview2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Project:LearnRecyleRview
 * Created by Fuxiang.Zhang
 * Time: 2016/8/9 14:17
 * Describe:自定义adapter继承自RecyclerView.Adapter
 */

public class MyAdapter extends RecyclerView.Adapter implements onMoveAndSwipedListener{
    private List<ItemData> datas;
    private Context context;
    private LayoutInflater inflater;
    //  回调接口设置
    private OnItemClickListener mOnItemClickListener;

//  定义构造函数将MainActivity中的context和datas传到adapter里
    public MyAdapter(Context context, List<ItemData> datas){
        this.context=context;
        this.datas=datas;
        inflater=LayoutInflater.from(context);
    }
//  主要生成为每个Item inflater出一个View，但是该方法返回的是一个ViewHolder。该方法把View直接封装在ViewHolder中，
// 然后我们面向的是ViewHolder这个实例，自己定义MyViewHolder继承自RecyclerView.ViewHolder。
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.recylerview_item,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
        return holder;

    }
//  用于适配渲染数据到View中
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        ItemData itemData= datas.get(position);

        myViewHolder.tv_title.setText(itemData.title);
        myViewHolder.tv_content.setText(itemData.content);

        if (mOnItemClickListener!=null){
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onClick(position);
                }
            });
            myViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mOnItemClickListener.onLongClick(position);
                    return true;
                }
            });

        }
    }
//  统计item的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }


    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        //交换mItems数据的位置
        Collections.swap(datas,fromPosition,toPosition);
        //交换RecyclerView列表中item的位置
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        datas.remove(position);
        notifyItemRemoved(position);
    }

    //  定义MyViewHolder继承自RecyclerView.ViewHolder，对item控件定位
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_content;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title);
            tv_content= (TextView) itemView.findViewById(R.id.tv_content);
        }

    }
//  recylerview没有提供点击监听事件，需要自己设置点击监听回调
    public interface OnItemClickListener{
        void onClick(int position);
        void onLongClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;
    }

    public void addData(int position){
        datas.add(position,new ItemData("标题"+position,"内容"+position));
        notifyItemInserted(position);
    }
    public void removeData(int position){
        datas.remove(position);
        notifyItemRemoved(position);
    }


}
