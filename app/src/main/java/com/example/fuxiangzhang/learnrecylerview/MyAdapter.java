package com.example.fuxiangzhang.learnrecylerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Project:LearnRecyleRview
 * Created by Fuxiang.Zhang
 * Time: 2016/8/8 15:55
 * Describe:
 */
class MyAdapter extends RecyclerView.Adapter {

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title,tv_content;

        public ViewHolder(View root) {
            super(root);
            tv_title= (TextView) root.findViewById(R.id.tv_title);
            tv_content= (TextView) root.findViewById(R.id.tv_content);

        }

        public TextView getTv_content() {
            return tv_content;
        }

        public TextView getTv_title() {
            return tv_title;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //解析布局文件
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder vh = (ViewHolder) holder;
        ItemData itemData=data[position];

        vh.getTv_title().setText(itemData.title);
        vh.getTv_content().setText(itemData.content);
    }

    //          获取子对象的数量
    @Override
    public int getItemCount() {
        return data.length;
    }

    private ItemData[] data=new ItemData[]{new ItemData("标题","内容"),new ItemData("新闻","还不错")};

}
