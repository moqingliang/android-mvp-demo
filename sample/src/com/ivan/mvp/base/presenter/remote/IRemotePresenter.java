package com.ivan.mvp.base.presenter.remote;

import android.content.Context;

import com.ivan.mvp.base.callback.IRequestCallback;
import com.ivan.mvp.base.model.Params;
import com.ivan.mvp.base.presenter.IPresenter;

/**
 * 
 * @description 从远程服务器获取数据的Presenter
 * @author Ivan Mo
 * @date 2016年8月2日
 * @version 1.0
 */
public interface IRemotePresenter extends IPresenter {

	/**
	 * http get 请求
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param requestCallback
	 */
	public void get(Context context, String url, Params params,
			IRequestCallback requestCallback);

	/**
	 * http post 请求
	 * 
	 * @param context
	 * @param url
	 * @param params
	 * @param requestCallback
	 */
	public void post(Context context, String url, Params params,
			IRequestCallback requestCallback);

}
