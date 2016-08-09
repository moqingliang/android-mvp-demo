package com.ivan.mvp.app.activity;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.ivan.mvp.R;
import com.ivan.mvp.app.model.User;
import com.ivan.mvp.app.presenter.TestUserLocalPresenter;
import com.ivan.mvp.app.presenter.TestUserRemotePresenter;
import com.ivan.mvp.base.activity.BaseActivity;
import com.ivan.mvp.base.callback.AbstractRequestCallback;
import com.ivan.mvp.base.model.Package;
import com.ivan.mvp.base.model.Params;
import com.ivan.mvp.base.model.Result;

public class TestActivity extends BaseActivity implements OnClickListener {

	private TestUserRemotePresenter userRemotePresenter;

	private TestUserLocalPresenter userLocalPresenter;

	public TestActivity() {
		// super(MainActivity.class.getName());
		// userRemotePresenter = new TestUserRemotePresenter();
		// userLocalPresenter = new TestUserLocalPresenter();
		super(TestActivity.class.getName(), new TestUserRemotePresenter(),
				new TestUserLocalPresenter());
		userRemotePresenter = (TestUserRemotePresenter) iPresenters[0];
		userLocalPresenter = (TestUserLocalPresenter) iPresenters[1];
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		Log.d(TAG, "onCreate");

		button(R.id.btn1).setOnClickListener(this);
		button(R.id.btn2).setOnClickListener(this);
		button(R.id.btn3).setOnClickListener(this);

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
		case R.id.btn1:
			findById();
			break;
		case R.id.btn2:
			queryList();
			break;
		case R.id.btn3:
			queryPackage();
			break;

		default:
			break;
		}
	}

	private void findById() {
		// TODO Remote test
		userRemotePresenter.findById(this, "U001",
				new AbstractRequestCallback<Result<User>>() {

					@Override
					public void onRequestSuccess(Result<User> result) {
						User user = result.getBody();
						String tips = "\nID：" + user.getId() + "\nUserName："
								+ user.getName() + "\nAge：" + user.getAge()
								+ "\n";
						showMessage(tips);
					}

					@Override
					public void onRequestFailure(Throwable error) {
						showMessage("请求接口findById失败");
					}
				});
		// TODO Local test
		User user = userLocalPresenter.findById("U001");
		Log.d(TAG, "Just for test. Get bean from local "
				+ (user == null ? true : false));
	}

	private void queryList() {
		// QueryString. Maybe from TextView, EditText...
		Params params = new Params();

		// TODO Remote test
		userRemotePresenter.queryList(this, params,
				new AbstractRequestCallback<Result<List<User>>>() {

					@Override
					public void onRequestStart() {
						showProgress("Loading...");
					}

					@Override
					public void onRequestSuccess(Result<List<User>> result) {
						List<User> users = result.getBody();
						String tips = "";
						for (User user : users) {
							tips += "\nID：" + user.getId() + "\nUserName："
									+ user.getName() + "\nAge：" + user.getAge()
									+ "\n";
						}
						showMsgAndDisProgress(tips);
					}

					@Override
					public void onRequestFailure(Throwable error) {
						showMsgAndDisProgress("请求接口queryList失败");
					}
				});
		// TODO Local test
		List<User> users = userLocalPresenter.queryList(params);
		Log.d(TAG, "Just for test. Get List from local "
				+ (users == null ? true : false));
	}

	private void queryPackage() {
		// QueryString. Maybe from TextView, EditText...
		Params params = new Params();

		// TODO Remote test
		userRemotePresenter.queryPackage(this, params,
				new AbstractRequestCallback<Result<Package<User>>>() {

					@Override
					public void onRequestSuccess(Result<Package<User>> result) {
						Package<User> pack = result.getBody();
						String tips = "\n当前页：" + pack.pageNo + "\n每页显示："
								+ pack.pageSize + "\n总记录：" + pack.count + "\n";
						for (User user : pack.datas) {
							tips += "\nID：" + user.getId() + "\nUserName："
									+ user.getName() + "\nAge：" + user.getAge()
									+ "\n";
						}
						showMessage(tips);
					}

					@Override
					public void onRequestFailure(Throwable error) {
						showMessage("请求接口queryPackage失败");
					}
				});
		// TODO Local test
		Package<User> pack = userLocalPresenter.queryPackage(params);
		Log.d(TAG, "Just for test. Get Package from local "
				+ (pack == null ? true : false));
	}

}
