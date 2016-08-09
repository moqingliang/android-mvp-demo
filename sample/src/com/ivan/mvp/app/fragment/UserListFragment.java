package com.ivan.mvp.app.fragment;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.ivan.mvp.R;
import com.ivan.mvp.app.model.User;
import com.ivan.mvp.app.presenter.TestUserLocalPresenter;
import com.ivan.mvp.app.presenter.TestUserRemotePresenter;
import com.ivan.mvp.base.callback.AbstractRequestCallback;
import com.ivan.mvp.base.fragment.BaseFragment;
import com.ivan.mvp.base.model.Params;
import com.ivan.mvp.base.model.Result;

public class UserListFragment extends BaseFragment implements OnClickListener {

	private TestUserRemotePresenter userRemotePresenter;

	private TestUserLocalPresenter userLocalPresenter;

	public UserListFragment() {
		super(UserListFragment.class.getName(), new TestUserRemotePresenter(),
				new TestUserLocalPresenter());
		userRemotePresenter = (TestUserRemotePresenter) iPresenters[0];
		userLocalPresenter = (TestUserLocalPresenter) iPresenters[1];
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user_list, container,
				false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initView();
		getDatas();
	}

	private void initView() {
	}

	@Override
	public void onClick(View v) {

	}

	private void getDatas() {
		// QueryString. Maybe from TextView, EditText...
		Params params = new Params();

		// TODO Remote test
		userRemotePresenter.queryList(mContext, params,
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

}
