package com.ivan.mvp.base.fragment.tab;

import java.util.HashMap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class FragmentTab {

	FragmentManager mFragmentManager;
	HashMap<String, TabItem> mTabItems = new HashMap<String, TabItem>();
	TabItem mSelectedTabItem;
	boolean cached;

	public FragmentTab(FragmentManager mFragmentManager) {
		this(mFragmentManager, false);
	}
	
	public FragmentTab(FragmentManager mFragmentManager, boolean cached) {
		this.mFragmentManager = mFragmentManager;
		this.cached = cached;
	}

	public void addTabItem(TabItemImpl<? extends Fragment> item) {
		mTabItems.put(item.getTag(), item);
	}

	public void selectTab(String tag) {
		selectTab(mTabItems.get(tag));
	}

	public void selectTab(TabItem tabItem) {
		final FragmentTransaction trans = mFragmentManager.beginTransaction()
				.disallowAddToBackStack();

		if (mSelectedTabItem == tabItem) {
			if (mSelectedTabItem != null) {
				mSelectedTabItem.onTabReselected(mSelectedTabItem, trans);
			}
		} else {
			if (cached) {
				// 隐藏所有Fragment
				for (TabItem tab : mTabItems.values()) {
					tab.onTabHide(tab, trans);
				}
				// 显示当前点击的Fragment
				mSelectedTabItem = tabItem;
				if (mSelectedTabItem != null) {
					mSelectedTabItem.onTabShow(mSelectedTabItem, trans);
				}
			} else {
				// 移除原来选择的Fragment
				if (mSelectedTabItem != null) {
					mSelectedTabItem.onTabUnselected(mSelectedTabItem, trans);
				}
				// 显示当前点击的Fragment
				mSelectedTabItem = tabItem;
				if (mSelectedTabItem != null) {
					mSelectedTabItem.onTabSelected(mSelectedTabItem, trans);
				}
			}

		}

		if (!trans.isEmpty()) {
			trans.commit();
		}
	}

}
