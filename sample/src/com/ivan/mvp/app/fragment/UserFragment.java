package com.ivan.mvp.app.fragment;

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
import com.ivan.mvp.base.model.Result;

public class UserFragment extends BaseFragment implements OnClickListener {

	private TestUserRemotePresenter userRemotePresenter;

	private TestUserLocalPresenter userLocalPresenter;

	public UserFragment() {
		super(UserFragment.class.getName(), new TestUserRemotePresenter(),
				new TestUserLocalPresenter());
		userRemotePresenter = (TestUserRemotePresenter) iPresenters[0];
		userLocalPresenter = (TestUserLocalPresenter) iPresenters[1];
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_user, container, false);
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
		// TODO Remote test
		userRemotePresenter.findById(mContext, "U001",
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

}
