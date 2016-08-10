package com.example.learnrecylerview2;

/**
 * Project:LearnRecyleRview
 * Created by Fuxiang.Zhang
 * Time: 2016/8/10 14:18
 * Describe:定义移动和侧滑的接口
 */

public interface onMoveAndSwipedListener {
        boolean onItemMove(int fromPosition,int toPosition);
        void onItemDismiss(int position);

}
