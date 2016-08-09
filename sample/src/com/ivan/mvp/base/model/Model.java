package com.ivan.mvp.base.model;

import java.io.Serializable;

/**
 * 
 * @description 自定义一个所有实体类的父类
 * @author Ivan Mo
 * @date 2016年8月2日
 * @version 1.0
 */
public class Model implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
