package com.ivan.mvp.utils;

import java.io.UnsupportedEncodingException;

import org.apache.http.Header;

import android.content.Context;
import android.util.Log;

import com.ivan.mvp.base.callback.IRequestCallback;
import com.ivan.mvp.base.model.Params;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class HttpUtil {

	private static AsyncHttpClient client = new AsyncHttpClient();
	private static final String charsetName = "GB2312";

	static {
		client.setTimeout(15000); // 设置链接超时，如果不设置，默认为15s
	}

	/**
	 * 用一个完整url获取一个string对象
	 * 
	 * @param context
	 * @param urlString
	 * @param res
	 */
	public static void get(Context context, String urlString, AsyncHttpResponseHandler res) {
		Log.i("HttpUtil", urlString);
		client.get(context, urlString, res);
	}

	/**
	 * url里面带参数
	 * 
	 * @param context
	 * @param urlString
	 * @param params
	 * @param callback
	 */
	public static void get(Context context, String url, Params params,
			final IRequestCallback callback) {

		RequestParams requestParams = params.convertToRequestParams();

		Log.i("HttpUtil", url + "?" + requestParams.toString());

		client.get(context, url, requestParams, new AsyncHttpResponseHandler() {

			@Override
			public void onStart() {
				super.onStart();
				callback.onRequestStart();
			}

			@Override
			public void onCancel() {
				super.onCancel();
				callback.onRequestCancel();
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				response(statusCode, responseBody, callback);
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				super.onProgress(bytesWritten, totalSize);
				callback.onRequestProgress(bytesWritten, totalSize);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				callback.onRequestFailure(error);
			}
		});
	}

	/**
	 * 不带参数，获取json对象或者数组
	 * 
	 * @param context
	 * @param urlString
	 * @param res
	 */
	public static void get(Context context, String urlString, JsonHttpResponseHandler res) {
		Log.i("HttpUtil", urlString);
		client.get(context, urlString, res);
	}

	/**
	 * 带参数，获取json对象或者数组
	 * 
	 * @param context
	 * @param urlString
	 * @param params
	 * @param res
	 */
	public static void get(Context context, String urlString, Params params,
			JsonHttpResponseHandler res) {
		RequestParams requestParams = params.convertToRequestParams();
		Log.i("HttpUtil", urlString + "?" + requestParams.toString());
		client.get(context, urlString, requestParams, res);
	}

	/**
	 * 下载数据使用，会返回byte数据
	 * 
	 * @param context
	 * @param uString
	 * @param bHandler
	 */
	public static void get(Context context, String uString, BinaryHttpResponseHandler bHandler) {
		Log.i("HttpUtil", uString);
		client.get(context, uString, bHandler);
	}

	/**
	 * url里面带参数
	 * 
	 * @param context
	 * @param urlString
	 * @param params
	 * @param callback
	 */
	public static void post(Context context, String url, Params params,
			final IRequestCallback callback) {
		RequestParams requestParams = params.convertToRequestParams();
		Log.i("HttpUtil", url + "?" + requestParams.toString());
		client.post(context, url, requestParams, new AsyncHttpResponseHandler() {

			@Override
			public void onStart() {
				super.onStart();
				callback.onRequestStart();
			}

			@Override
			public void onCancel() {
				super.onCancel();
				callback.onRequestCancel();
			}

			@Override
			public void onSuccess(int statusCode, Header[] headers,
					byte[] responseBody) {
				response(statusCode, responseBody, callback);
			}

			@Override
			public void onProgress(int bytesWritten, int totalSize) {
				super.onProgress(bytesWritten, totalSize);
				callback.onRequestProgress(bytesWritten, totalSize);
			}

			@Override
			public void onFailure(int statusCode, Header[] headers,
					byte[] responseBody, Throwable error) {
				callback.onRequestFailure(error);
			}
		});
	}

	public static AsyncHttpClient getClient() {
		return client;
	}

	private static void response(int statusCode, byte[] responseBody,
			IRequestCallback callback) {

		if (statusCode == 200) {
			try {
				String result = new String(responseBody, charsetName);
				Log.i("HttpUtil", result);
				callback.onRequestSuccess(result);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 取消请求
	 * 
	 * @param context
	 */
	public static void onCancelRequest(Context context) {
		client.cancelRequests(context, true);
	}
}
