package com.ivan.mvp.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.ivan.mvp.R;
import com.ivan.mvp.base.activity.BaseActivity;

public class MainActivity extends BaseActivity implements OnClickListener {

	public MainActivity() {
		super(MainActivity.class.getName());
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "onCreate");

		button(R.id.testActivity).setOnClickListener(this);
		button(R.id.testFragmentActivity).setOnClickListener(this);

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
		case R.id.testActivity:
			startActivity(new Intent(mContext, TestActivity.class));
			break;
		case R.id.testFragmentActivity:
			startActivity(new Intent(mContext, TestFragmentActivity.class));
			break;

		default:
			break;
		}
	}

}
