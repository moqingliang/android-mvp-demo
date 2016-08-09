package com.ivan.mvp.base.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @description 数据包
 * @author Ivan Mo
 * @date 2016年8月2日
 * @param <T>
 * @version 1.0
 */
public class Package<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 总行数
	 */
	public int count;

	/**
	 * 每页显示的行数
	 */
	public int pageSize;

	/**
	 * 页码
	 */
	public int pageNo;

	/**
	 * 当前页数据集
	 */
	public Collection<T> datas;

	/**
	 * 当前页数据
	 * 
	 * @return Returns the datas.
	 */
	public Collection<T> getDatas() {
		if (datas == null) {
			datas = new ArrayList<T>();
		}

		return datas;
	}

	/**
	 * 当前页数据
	 * 
	 * @param datas
	 *            The datas to set.
	 */
	public void setDatas(Collection<T> datas) {
		this.datas = datas;
	}

	/**
	 * 总行数
	 * 
	 * @return Returns the count.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 总行数
	 * 
	 * @param count
	 *            The count to set.
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 每页显示的行数
	 * 
	 * @return Returns the pageSize.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 每页显示的行数
	 * 
	 * @param pageSize
	 *            The pageSize to set.
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 页码
	 * 
	 * @return Returns the pageNo.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 页码
	 * 
	 * @param pageNo
	 *            The pageNo to set.
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	/**
	 * 总页数
	 * 
	 * @return The page count number.
	 */
	public int getPageCount() {
		return (int) Math.ceil((double) count / (double) pageSize);
	}

}
