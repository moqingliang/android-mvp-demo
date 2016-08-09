package com.ivan.mvp.app.presenter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.ivan.mvp.app.api.API;
import com.ivan.mvp.app.model.User;
import com.ivan.mvp.base.callback.IRequestCallback;
import com.ivan.mvp.base.model.Params;
import com.ivan.mvp.base.presenter.remote.AbstractRemotePresenter;
import com.ivan.mvp.utils.HttpUtil;

/**
 * 
 * @description Test get datas from remote server
 * @author Ivan Mo
 * @date 2016年8月3日
 * @version 1.0
 */
public class TestUserRemotePresenter extends AbstractRemotePresenter<User> {

	/**
	 * Bind life cycle with Activity/Fragment
	 */
	@Override
	public void onCreate(Context context, Bundle savedInstanceState) {
		super.onCreate(context, savedInstanceState);
		Log.d(TAG, "创建");
	}

	/**
	 * Bind life cycle with Activity/Fragment
	 */
	@Override
	public void onDestroy(Context context) {
		super.onDestroy(context);
		Log.d(TAG, "销毁");
	}

	/**
	 * Find user by id from remote server 跟据用户id查找用户
	 * 
	 * @param context
	 * @param id
	 * @param requestCallback
	 */
	public void findById(Context context, String id,
			IRequestCallback requestCallback) {
		String url = API.User.VO;
		Params params = new Params();
		params.setParameter("id", id);
		HttpUtil.get(context, url, params, requestCallback);
	}

	/**
	 * Query a list from remote server 查询用户列表
	 * 
	 * @param context
	 * @param params
	 * @param requestCallback
	 */
	public void queryList(Context context, Params params,
			IRequestCallback requestCallback) {
		String url = API.User.LIST;
		HttpUtil.get(context, url, params, requestCallback);
	}

	/**
	 * Query a package from remote server, include page info. 查询用户列表-分页查找
	 * 
	 * @param context
	 * @param params
	 * @param requestCallback
	 */
	public void queryPackage(Context context, Params params,
			IRequestCallback requestCallback) {
		String url = API.User.PACKAGE;
		HttpUtil.get(context, url, params, requestCallback);
	}

}
