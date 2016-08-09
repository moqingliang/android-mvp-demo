package com.ivan.mvp.app.activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.ivan.mvp.R;
import com.ivan.mvp.app.fragment.UserFragment;
import com.ivan.mvp.app.fragment.UserListFragment;
import com.ivan.mvp.app.fragment.UserPackageFragment;
import com.ivan.mvp.base.fragment.tab.FragmentTab;
import com.ivan.mvp.base.fragment.tab.TabItemImpl;

public class TestFragmentActivity extends FragmentActivity implements
		OnClickListener {

	private final String TAG = TestFragmentActivity.class.getSimpleName();

	private Context mContext;

	private static final int[] tabIcon = new int[] {
			R.drawable.tab_menu_homepage, R.drawable.tab_menu_message,
			R.drawable.tab_menu_mine };

	private static final int[] tabIconSelector = new int[] {
			R.drawable.tab_menu_homepage_selector,
			R.drawable.tab_menu_message_selector,
			R.drawable.tab_menu_mine_selector };

	private TextView[] tabs = new TextView[3];

	private FragmentTab mFragmentTab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
		super.onCreate(savedInstanceState);
		mContext = this;
		setContentView(R.layout.activity_fragment_test);

		initView();

	}

	private void initView() {

		mFragmentTab = new FragmentTab(getSupportFragmentManager());
		// 首页
		mFragmentTab.addTabItem(new TabItemImpl<UserFragment>(mContext, "homepage", UserFragment.class));
		// 消息
		mFragmentTab.addTabItem(new TabItemImpl<UserListFragment>(mContext, "message", UserListFragment.class));
		// 我的
		mFragmentTab.addTabItem(new TabItemImpl<UserPackageFragment>(mContext, "mine", UserPackageFragment.class));

		tabs[0] = (TextView) findViewById(R.id.tv_homepage);// 首页
		tabs[1] = (TextView) findViewById(R.id.tv_message);// 消息
		tabs[2] = (TextView) findViewById(R.id.tv_mine);// 我的

		tabs[0].setOnClickListener(this);
		tabs[1].setOnClickListener(this);
		tabs[2].setOnClickListener(this);

		doSelect(0);// 默认打开首页
	}

	/**
	 * 一级菜单选中状态事件。
	 */
	private void doSelect(int position) {

		switch (position) {
		case 0:
			mFragmentTab.selectTab("homepage");// 首页
			break;
		case 1:
			mFragmentTab.selectTab("message");// 消息
			break;
		case 2:
			mFragmentTab.selectTab("mine");// 我的
			break;
		default:
			break;
		}

		Resources res = getResources();
		for (int i = 0; i < tabIcon.length; i++) {
			if (i == position) {
				Drawable selected = res.getDrawable(tabIconSelector[i]);
				selected.setBounds(0, 0, selected.getMinimumWidth(),
						selected.getMinimumHeight());
				tabs[i].setCompoundDrawables(null, selected, null, null);
				tabs[i].setTextColor(res.getColor(R.color.blue));

			} else {
				Drawable unselected = res.getDrawable(tabIcon[i]);
				unselected.setBounds(0, 0, unselected.getMinimumWidth(),
						unselected.getMinimumHeight());
				tabs[i].setCompoundDrawables(null, unselected, null, null);
				tabs[i].setTextColor(res.getColor(R.color.gray));
			}
		}
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.d(TAG, "onResume");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.d(TAG, "onDestroy");
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.d(TAG, "onPause");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.d(TAG, "onStop");
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.d(TAG, "onStart");
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		Log.d(TAG, "onRestart");
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_homepage:
			doSelect(0);;// 首页
			break;
		case R.id.tv_message:
			doSelect(1);;// 消息
			break;
		case R.id.tv_mine:
			doSelect(2);;// 我的
			break;

		default:
			break;
		}
	}

}
