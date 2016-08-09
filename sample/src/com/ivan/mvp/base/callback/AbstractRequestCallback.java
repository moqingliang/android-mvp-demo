package com.ivan.mvp.base.callback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.internal.$Gson$Types;
import com.ivan.mvp.utils.GsonUtil;

/**
 * 
 * @description 把回调数据Json解析成beans的一个默认实现
 * @author Ivan Mo
 * @date 2016年8月2日
 * @param <T>
 * @version 1.0
 */
public abstract class AbstractRequestCallback<T> implements IRequestCallback {

	/**
	 * GSON解析beans需要的类型Type
	 */
	protected Type mType;

	public AbstractRequestCallback() {
		setSuperclassTypeParameter(getClass());
	}

	private void setSuperclassTypeParameter(Class<?> subClass) {
		Type superClass = subClass.getGenericSuperclass();
		if (superClass instanceof Class) {
			throw new RuntimeException("Missing type parameter.");
		}
		ParameterizedType parameterized = (ParameterizedType) superClass;
		Type type = parameterized.getActualTypeArguments()[0];
		this.mType = $Gson$Types.canonicalize(type);
	}
	
	@Override
	public void onRequestStart() {
	}

	@Override
	public void onRequestCancel() {
	}

	@Override
	public void onRequestProgress(int bytesWritten, int totalSize) {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onRequestSuccess(String response) {

		// 测试
//		if (url.contains("baidu")) {
//			response = "{\"code\": 0,\"message\": \"查询成功\",\"body\":{\"id\": \"U001\",\"name\":\"张三\", \"age\": 27}}";
//		} else if (url.contains("163")) {
//			response = "{\"code\": 0,\"message\": \"查询成功\",\"body\":{\"pageNo\":1, \"count\":25, \"pageSize\": 3, \"datas\":[{\"id\": \"D001\",\"name\":\"财务部\", \"level\": 1},{\"name\":\"市场部\", \"level\": 2}]}}";
//		} else {
//			response = "{\"code\": 0,\"message\": \"查询成功\",\"body\":[{\"id\": \"U001\",\"name\":\"张三\", \"age\": 27}, {\"name\":\"李四\", \"age\": 23}]}";
		//}
	
		try {
			// Convert jsonStr to actual type
			T t = (T) GsonUtil.getInstance().convertJsonStringToObject(
					response, mType);

			// Return converted data
			onRequestSuccess(t);
			
		} catch (Exception e) {
			// Convert exception occurred
			onRequestFailure(e);
		}
	}

	public abstract void onRequestSuccess(T t);

	@Override
	public abstract void onRequestFailure(Throwable error);

}
