package com.ivan.mvp.base.presenter.remote;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.ivan.mvp.base.callback.IRequestCallback;
import com.ivan.mvp.base.model.Params;
import com.ivan.mvp.utils.HttpUtil;

public abstract class AbstractRemotePresenter<T> implements IRemotePresenter {

	protected final String TAG = this.getClass().getSimpleName();

	@Override
	public void onCreate(Context context, Bundle savedInstanceState) {
		Log.d(TAG, "onCreate");
	}

	@Override
	public void onStart(Context context) {
		Log.d(TAG, "onStart");
	}

	@Override
	public void onRestart(Context context) {
		Log.d(TAG, "onRestart");
	}

	@Override
	public void onResume(Context context) {
		Log.d(TAG, "onResume");
	}

	@Override
	public void onPause(Context context) {
		Log.d(TAG, "onPause");
	}

	@Override
	public void onStop(Context context) {
		Log.d(TAG, "onStop");
	}

	@Override
	public void onDestroy(Context context) {
		Log.d(TAG, "onDestroy");
		HttpUtil.onCancelRequest(context);
	}

	@Override
	public void get(Context context, String url, Params params,
			IRequestCallback requestCallback) {
		HttpUtil.get(context, url, params, requestCallback);
	}

	@Override
	public void post(Context context, String url, Params params,
			IRequestCallback requestCallback) {
		HttpUtil.post(context, url, params, requestCallback);
	}

}
