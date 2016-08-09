package com.ivan.mvp.base.fragment.tab;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.ivan.mvp.R;

public class TabItemImpl<T extends Fragment> extends TabItem implements TabListener {

	public TabItemImpl(Context ctx, String mTag, Class<T> mClass) {
		super(ctx, mTag, mClass);
	}

	public TabItemImpl(Context ctx, String mTag, Class<T> mClass, Bundle mArgs) {
		super(ctx, mTag, mClass, mArgs);
	}

	@Override
	public void onTabAdd(TabItem tab, FragmentTransaction ft) {
		mFragment = Fragment.instantiate(mContext, mClass.getName(), mArgs);
		ft.add(R.id.content, mFragment, mTag);
	}

	@Override
	public void onTabHide(TabItem tab, FragmentTransaction ft) {
		if (mFragment != null) {
			ft.hide(mFragment);
		}
	}

	@Override
	public void onTabShow(TabItem tab, FragmentTransaction ft) {
		if (mFragment == null) {
			onTabAdd(tab, ft);
		} else {
			ft.show(mFragment);
		}
	}
	
	@Override
	public void onTabSelected(TabItem tab, FragmentTransaction ft) {
		if (mFragment == null) {
			onTabAdd(tab, ft);
		} else {
			ft.attach(mFragment);
		}
	}

	@Override
	public void onTabUnselected(TabItem tab, FragmentTransaction ft) {
		if (mFragment != null) {
			ft.remove(mFragment);
			mFragment = null;
		}
	}

	@Override
	public void onTabReselected(TabItem tab, FragmentTransaction ft) {
		// ToDo
	}

}
