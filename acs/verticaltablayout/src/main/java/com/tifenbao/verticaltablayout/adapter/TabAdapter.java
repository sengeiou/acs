package com.tifenbao.verticaltablayout.adapter;


import com.tifenbao.verticaltablayout.widget.TabView;

/**
 * @author chqiu
 *         Email:qstumn@163.com
 */
public interface TabAdapter {
    int getCount();

    TabView.TabBadge getBadge(int position);

    TabView.TabIcon getIcon(int position);

    TabView.TabTitle getTitle(int position);

    TabView.TabBackground getTabBackground(int position);

    int getBackground(int position);
}
