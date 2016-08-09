package com.ivan.mvp.base.fragment.tab;

import android.support.v4.app.FragmentTransaction;

public interface TabListener {
	
	public void onTabAdd(TabItem tab, FragmentTransaction ft);
	
	public void onTabHide(TabItem tab, FragmentTransaction ft);
	
	public void onTabShow(TabItem tab, FragmentTransaction ft);
	
	public void onTabSelected(TabItem tab, FragmentTransaction ft);

    public void onTabUnselected(TabItem tab, FragmentTransaction ft);

    public void onTabReselected(TabItem tab, FragmentTransaction ft);

}
