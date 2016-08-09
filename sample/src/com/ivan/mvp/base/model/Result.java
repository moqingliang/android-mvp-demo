package com.ivan.mvp.base.model;

import java.io.Serializable;
/**
 * @description ResultSet
 * @author Ivan Mo
 * @date 2016年8月2日
 * @param <T> body type
 * @version 1.0
 */
public final class Result<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Result code (0：success or -1：failed or ...)
	 */
	private int code;
	
	/**
	 * Result message ("保存成功" or "OK" or ...)
	 */
	private String message;
	
	/**
	 * Result body.The Received data 
	 */
	private T body;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

}
