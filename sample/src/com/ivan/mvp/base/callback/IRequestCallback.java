package com.ivan.mvp.base.callback;

/**
 * Request Callback
 * 
 * @author Ivan Mo
 * 
 */
public interface IRequestCallback {
	/**
	 * Request start
	 * 
	 */
	public void onRequestStart();

	/**
	 * Request cancel
	 * 
	 */
	public void onRequestCancel();

	/**
	 * Request progress
	 * 
	 * @param bytesWritten
	 * 
	 * @param totalSize
	 */
	public void onRequestProgress(int bytesWritten, int totalSize);

	/**
	 * Request success
	 * 
	 * @param response
	 */
	public void onRequestSuccess(String response);

	/**
	 * Request failure
	 * 
	 * @param error
	 */
	public void onRequestFailure(Throwable error);
}
