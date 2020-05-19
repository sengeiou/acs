package com.tifenbao.base.view.recycleview;

/**
 * Created by luojg on 2016/12/6.
 */
public abstract class DrawRegularListener {

    public static int TYPE_DRAW_NORMAL = 0;
    public static int TYPE_NOT_DRAW_VERTICAL = 1;
    public static int TYPE_NOT_DRAW_HORIZONTAL = 2;
    public static int TYPE_NOT_DRAW_VERTICAL_AND_HORIZONTAL = 3;

    public int isDrawDivider(int pos){
        return TYPE_DRAW_NORMAL;
    }

    public int getColumn(int pos){
        return 0;
    }
    
    public int getColumnCount(int pos){
        return 1;
    }
}
