package com.example.learnrecylerview2;

/**
 * Project:LearnRecyleRview
 * Created by Fuxiang.Zhang
 * Time: 2016/8/10 14:18
 * Describe:
 */

public interface onMoveAndSwipedListener {
        boolean onItemMove(int fromPosition,int toPosition);
        void onItemDismiss(int position);

}
