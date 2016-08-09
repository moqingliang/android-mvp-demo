package com.ivan.mvp.base.presenter;

import android.content.Context;
import android.os.Bundle;

/**
 * Bind life cycle with Activity/Fragment
 * @description Presenter顶层接口，与Activity生命周期绑定
 * @author Ivan Mo
 * @date 2016年8月2日
 * @version 1.0
 */
public interface IPresenter {

	public void onCreate(Context context, Bundle savedInstanceState);

	public void onStart(Context context);

	public void onRestart(Context context);

	public void onResume(Context context);

	public void onPause(Context context);

	public void onStop(Context context);

	public void onDestroy(Context context);

}
