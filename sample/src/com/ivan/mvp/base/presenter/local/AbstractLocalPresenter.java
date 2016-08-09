package com.ivan.mvp.base.presenter.local;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.ivan.mvp.base.model.Package;
import com.ivan.mvp.base.model.Params;

/**
 * 
 * @description 从sqlite数据库获取数据，可集成GreenDao这个ORM框架对sqlite数据库操作
 * @author Ivan Mo
 * @date 2016年8月2日
 * @param <T>
 * @version 1.0
 */
public abstract class AbstractLocalPresenter<T> implements ILocalPresenter<T> {

	protected final String TAG = this.getClass().getSimpleName();

	/**
	 * 持久化实体类的Class
	 */
	protected Class<T> mClass;

	public AbstractLocalPresenter() {
		setSuperclassClassParameter(getClass());
	}

	@SuppressWarnings("unchecked")
	private void setSuperclassClassParameter(Class<?> subClass) {
		Type superClass = subClass.getGenericSuperclass();
		if (superClass instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameterized = (ParameterizedType) superClass;
		Type type = parameterized.getActualTypeArguments()[0];
		this.mClass = (Class<T>) type;
	}

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
	}

	@Override
	public T findById(String id) {
		// TODO 此处不再模拟下去了，用户可集成GreenDao这个ORM框架对sqlite数据库操作
		// return daoSession.load(mClass, id);
		return null;
	}

	@Override
	public List<T> queryList(Params params) {
		// TODO 此处不再模拟下去了，用户可集成GreenDao这个ORM框架对sqlite数据库操作
		// select from tableName where columnName like 'Ivan'
		// return
		// daoSession.getXXXDao().queryBuilder().where(XXXDao.Properties.columnName.like("Ivan")).build().list();
		return null;
	}

	@Override
	public Package<T> queryPackage(Params params) {
		// TODO 此处不再模拟下去了，用户可集成GreenDao这个ORM框架对sqlite数据库操作
		// select from tableName where columnName like 'Ivan'
		// return
		// daoSession.getXXXDao().queryBuilder().where(XXXDao.Properties.columnName.like("Ivan")).build().list();
		return null;
	}

	@Override
	public boolean saveOrUpdate(T object) {
		// TODO 此处不再模拟下去了，用户可集成GreenDao这个ORM框架对sqlite数据库操作
		// return daoSession.getXXXDao().insertOrReplace(object);
		return true;
	}

	@Override
	public boolean delete(String id) {
		// TODO 此处不再模拟下去了，用户可集成GreenDao这个ORM框架对sqlite数据库操作
		// return daoSession.getXXXDao().deleteByKey(id);
		return true;
	}

}
