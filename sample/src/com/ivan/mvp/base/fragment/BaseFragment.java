package com.ivan.mvp.base.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ivan.mvp.base.presenter.IPresenter;

public class BaseFragment extends Fragment {

	protected String TAG;

	public static final Handler handler = new Handler();

	protected final static int DATA_LOAD_ING = 0x001;
	protected final static int DATA_LOAD_COMPLETE = 0x002;
	protected final static int DATA_LOAD_FAIL = 0x003;

	/**
	 * 主activity
	 */
	public Context mContext;

	public View fragment;

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
	 * 
	 * @param tag
	 * @param presenter
	 *            传入(不传则自行维护Presenter生命周期)Activity用到的所有Presenter,
	 *            BaseActivity即可与Presenter保持生命周期一致
	 */
	public BaseFragment(String tag, IPresenter... presenter) {
		for (int i = 0; i < presenter.length; i++) {
			if (presenter[i] == null) {
				throw new IllegalArgumentException("The IPresenter is null.");
			}
		}
		this.TAG = tag;
		this.iPresenters = presenter;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onCreate(mContext, savedInstanceState);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mContext = getActivity();
		this.fragment = getView();
	}

	@Override
	public void onStart() {
		super.onStart();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onStart(mContext);
		}
	}

	@Override
	public void onResume() {
		super.onResume();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onResume(mContext);
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onDestroy(mContext);
		}
		if (progress != null) {
			progress.dismiss();
		}
	}

	@Override
	public void onPause() {
		super.onPause();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onPause(mContext);
		}
		if (progress != null) {
			progress.dismiss();
		}
	}

	@Override
	public void onStop() {
		super.onStop();
		for (int i = 0; i < iPresenters.length; i++) {
			iPresenters[i].onStop(mContext);
		}
		if (progress != null) {
			progress.dismiss();
		}
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
		progress = new ProgressDialog(mContext);
		progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		progress.setMessage(message);
		progress.setCancelable(true);
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
		BaseFragment.handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
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
		BaseFragment.handler.post(new Runnable() {
			@Override
			public void run() {
				Toast toast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
				toast.setGravity(Gravity.CENTER, 0, 0);
				toast.show();
				disShowProgress();
			}
		});
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

	/**
	 * 获取TextView
	 */
	public TextView findTextViewById(int id) {
		return (TextView) fragment.findViewById(id);
	}

	/**
	 * 获取Button
	 * 
	 * @param id
	 * @return
	 */
	public Button findButtonById(int id) {
		return (Button) fragment.findViewById(id);
	}

	/**
	 * 获取ImageButton
	 * 
	 * @param id
	 * @return
	 */
	public ImageButton findImageButtonById(int id) {
		return (ImageButton) fragment.findViewById(id);
	}

	/**
	 * 获取ImageView
	 * 
	 * @param id
	 * @return
	 */
	public ImageView findImageViewById(int id) {
		return (ImageView) fragment.findViewById(id);
	}

	/**
	 * 获取LinearLayout
	 * 
	 * @param id
	 * @return
	 */
	public LinearLayout findLayoutById(int id) {
		return (LinearLayout) fragment.findViewById(id);
	}

	/**
	 * 获取RelativeLayout
	 * 
	 * @param id
	 * @return
	 */
	public RelativeLayout findRelativeLayoutById(int id) {
		return (RelativeLayout) fragment.findViewById(id);
	}

	/**
	 * 获取LinearLayout
	 * 
	 * @param id
	 * @return
	 */
	public EditText findEditTextById(int id) {
		return (EditText) fragment.findViewById(id);
	}

	/**
	 * 获取TableRow
	 * 
	 * @param id
	 * @return
	 */
	public TableRow findTableRowById(int id) {
		return (TableRow) fragment.findViewById(id);
	}

	/**
	 * 进入Activity
	 */
	public void goActivity(Class<?> inClass) {
		Intent intent = new Intent(mContext, inClass);
		startActivity(intent);
	}

	/**
	 * 进入Activity
	 */
	public void goActivityAddBundle(Class<?> inClass, Bundle bundle) {
		Intent intent = new Intent(mContext, inClass);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
