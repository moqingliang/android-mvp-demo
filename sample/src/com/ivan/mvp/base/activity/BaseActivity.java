package com.ivan.mvp.base.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ivan.mvp.base.presenter.IPresenter;

public class BaseActivity extends Activity {

	protected String TAG;

	public static final Handler handler = new Handler();

	protected final static int DATA_LOAD_ING = 0x001;
	protected final static int DATA_LOAD_COMPLETE = 0x002;
	protected final static int DATA_LOAD_FAIL = 0x003;

	/**
	 * 上下文 当进入activity时设置 mContext = this，否则默认使用BaseActivity的Context
	 */
	public Context mContext;

	/**
	 * 加载等待效果
	 */
	public ProgressDialog progress;

	/**
	 * IPresenter
	 */
	protected IPresenter[] iPresenters;

	/**
	 * 构造方法
	 * @param tag
	 * @param presenter
	 * 			传入(不传则自行维护Presenter生命周期)Activity用到的所有Presenter, BaseActivity即可与Presenter保持生命周期一致
	 */
	public BaseActivity(String tag, IPresenter... presenter) {
		for (int i = 0; i < presenter.length; i++) {
			if (presenter[i] == null) {
				throw new IllegalArgumentException("The IPresenter is null.");
			}
		}
		this.TAG = tag;
		this.iPresenters = presenter;
	}

	/**
	 * 初始化创建
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onCreate(mContext, savedInstanceState);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onStart(mContext);
		}
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onRestart(mContext);
		}
	}

	/**
	 * 重回前台显示调用
	 */
	@Override
	protected void onResume() {
		super.onResume();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onResume(mContext);
		}
	}

	/**
	 * Activity暂停，关闭加载效果
	 */
	@Override
	protected void onPause() {
		super.onPause();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onPause(mContext);
		}
		if (progress != null) {
			progress.dismiss();
		}
	}

	/**
	 * Activity停止，关闭加载效果
	 */
	@Override
	protected void onStop() {
		super.onStop();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onStop(mContext);
		}
		if (progress != null) {
			progress.dismiss();
		}
	}

	/**
	 * Activity销毁，关闭加载效果
	 */
	@Override
	protected void onDestroy() {
		super.onDestroy();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onDestroy(mContext);
		}
		if (progress != null) {
			progress.dismiss();
		}
	}

	/**
	 * 触发手机返回按钮
	 */
	@Override
	public void onBackPressed() {
		// MyActivityManager.getInstance().removeActivity(Container.this);
		finish();
	}

	/**
	 * 显示字符串消息
	 * 
	 * @param message
	 */
	public void showProgress(String message) {
		if (progress != null) {
			progress.dismiss();
		}
		progress = new ProgressDialog(BaseActivity.this);
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setMessage(message);
		progress.setCancelable(false);
		progress.show();
	}

	/**
	 * 隐藏字符串消息
	 */
	public void disShowProgress() {
		if (progress != null) {
			progress.dismiss();
		}
	}

	/**
	 * 提示信息
	 * 
	 * @param str
	 */
	public void showMessage(String message) {
		final String str = message;
		BaseActivity.handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(getApplicationContext(), str,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	}

	/**
	 * 提示信息号或请求失败信息
	 * 
	 * showErrorRequestFair
	 * 
	 * @param str
	 */
	public void showE404() {
		BaseActivity.handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(getApplicationContext(),
						"手机信号差或服务器维护，请稍候重试。谢谢！", Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
			}
		});
	}

	/**
	 * 提示信息
	 * 
	 * @param str
	 */
	public void showMsgAndDisProgress(String message) {
		final String str = message;
		BaseActivity.handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(getApplicationContext(), str,
						Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				disShowProgress();
			}
		});
	}

	/**
	 * 文本View
	 */
	public TextView textView(int textview) {
		return (TextView) findViewById(textview);
	}

	/**
	 * 文本button
	 */
	public Button button(int id) {
		return (Button) findViewById(id);
	}

	/**
	 * 文本button
	 */
	public ImageView imageView(int id) {
		return (ImageView) findViewById(id);
	}

	/**
	 * 文本editText
	 */
	public EditText editText(int id) {
		return (EditText) findViewById(id);
	}

	/**
	 * 显示数据加载状态
	 * 
	 * @param loading
	 * @param failed
	 * @param datas
	 * @param type
	 */
	public void viewSwitch(View loading, View datas, int type) {
		switch (type) {
		case DATA_LOAD_ING:
			datas.setVisibility(View.GONE);
			loading.setVisibility(View.VISIBLE);
			break;
		case DATA_LOAD_COMPLETE:
			datas.setVisibility(View.VISIBLE);
			loading.setVisibility(View.GONE);
			break;
		case DATA_LOAD_FAIL:
			datas.setVisibility(View.GONE);
			loading.setVisibility(View.GONE);
			break;
		}
	}
	
	@Override
	public void startActivity(Intent intent) {
		// Custom your enterAnim/exitAnim here
		super.startActivity(intent);
	}

}
