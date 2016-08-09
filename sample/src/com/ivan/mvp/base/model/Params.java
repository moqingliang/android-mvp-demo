package com.ivan.mvp.base.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.loopj.android.http.RequestParams;

import android.text.TextUtils;

/**
 * 
 * @description 参数
 * @author Ivan Mo
 * @date 2016年8月2日
 * @version 1.0
 */
public class Params implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 初始化参数集合(Map)
	 */
	private HashMap<String, Object> params = new HashMap<String, Object>();

	public Params() {

	}

	public Params(Params params) {
		this.params.putAll(params.getParams());
	}

	/**
	 * 设置参数
	 * 
	 * @param name
	 *            参数名
	 * @param value
	 *            参数值
	 */
	public void setParameter(String name, Object value) {
		if (value instanceof String) {
			params.put(name, (String) value);
		} else {
			params.put(name, value);
		}
	}

	/**
	 * 取出参数
	 * 
	 * @param name
	 *            参数名
	 * @return 参数值
	 */
	public Object getParameter(String name) {
		return params.get(name);
	}

	/**
	 * 取出参数值并格式化成字符串(java.lang.String)
	 * 
	 * @param name
	 *            参数名
	 * @return 参数值
	 */
	public String getParameterAsString(String name) {
		Object obj = params.get(name);

		if (obj instanceof String)
			return (String) obj;
		else if (obj instanceof String[])
			return TextUtils.join(";", (String[]) obj);
		else if (obj instanceof Object[])
			return TextUtils.join(";", (Object[]) obj);
		else
			return (obj != null) ? obj.toString() : null;
	}

	/**
	 * 取出参数值并格式化成数组
	 * 
	 * @param name
	 *            参数名
	 * @return 参数值
	 */
	public String[] getParameterAsArray(String name) {
		Object obj = params.get(name);
		if (obj != null) {
			if (obj instanceof String[]) {
				return (String[]) obj;
			} else {
				return new String[] { (String) obj };
			}
		} else {
			return new String[] {};
		}
	}

	/**
	 * 取出参数值并格式化成文本型
	 * 
	 * @param name
	 *            参数名
	 * @return 参数值
	 */
	public String getParameterAsText(String name) {
		return getParameterAsText(name, ";");
	}

	/**
	 * 取出参数值并格式化成文本型,以参数可进行切割
	 * 
	 * @param name
	 *            参数名
	 * @param split
	 *            切割字符
	 * @return 参数值
	 */
	public String getParameterAsText(String name, String split) {
		Object obj = params.get(name);

		if (obj instanceof String)
			return (String) obj;
		else if (obj instanceof String[])
			return TextUtils.join(";", (String[]) obj);
		else if (obj instanceof Object[])
			return TextUtils.join(";", (Object[]) obj);
		else
			return (obj != null) ? obj.toString() : null;
	}

	/**
	 * 取出参数值并格式化成Double型
	 * 
	 * @param name
	 *            参数名
	 * @return 参数值
	 */
	public Double getParameterAsDouble(String name) {
		Object obj = params.get(name);
		if (obj instanceof String) {
			String value = (String) obj;

			try {
				return Double.valueOf(value);
			} catch (Exception e) {
				return Double.valueOf(0.0);
			}
		}

		return null;
	}

	public Integer getParameterAsInteger(String name) {
		Object obj = params.get(name);
		if (obj instanceof String) {
			String value = (String) obj;

			try {
				return Integer.valueOf(value);
			} catch (Exception e) {
				return Integer.valueOf(0);
			}
		}

		return null;
	}

	public boolean getParameterAsBoolean(String name) {
		Object obj = params.get(name);
		if (obj instanceof String) {
			String value = (String) obj;

			if (value.equals("true")) {
				return true;
			}
		} else if (obj instanceof Boolean) {// 增加了对obj是否Boolean类型的判断
			return Boolean.parseBoolean(obj.toString());
		}

		return false;
	}

	/**
	 * 取出参数值并格式化成Long型
	 * 
	 * @param name
	 *            参数名
	 * @return 参数值
	 */
	public Long getParameterAsLong(String name) {
		Object obj = params.get(name);

		if (obj instanceof String) {
			String value = (String) obj;
			try {
				return Long.valueOf(value);
			} catch (Exception e) {
				return Long.valueOf(0);
			}
		}

		return null;
	}

	/**
	 * 返回参数名迭代器(Iterator)
	 * 
	 * @return The parameter names.
	 */
	public Iterator<String> getParameterNames() {
		return params.keySet().iterator();
	}

	/**
	 * 取出参数值并格式化成Arrary型
	 * 
	 * @param name
	 *            参数名
	 * @param index
	 *            arrary[index]
	 * @return 参数值
	 */
	public Object getParameter(String name, int index) {
		Object obj = params.get(name);
		if (obj != null && obj instanceof String[]) {
			String[] col = (String[]) obj;
			return col[index];
		}
		return null;
	}

	/**
	 * 设置参数对Array
	 * 
	 * @param name
	 *            参数名
	 * @param index
	 *            arrary[index]
	 * @param value
	 *            参数值
	 */
	public void setParameter(String name, int index, Object value) {
		Object obj = params.get(name);
		if (obj != null && obj instanceof String[]) {
			String[] col = (String[]) obj;
			col[index] = (String) value;
		}
	}

	/**
	 * 移除参数
	 * 
	 * @param name
	 *            参数名
	 */
	public void removeParameter(String name) {
		params.remove(name);
	}

	/**
	 * Put all the map
	 * 
	 * @param map
	 *            The map
	 */
	public void putAll(Map<String, ?> map) {
		this.params.putAll(map);
	}

	public Map<String, ?> getParams() {
		return this.params;
	}

	public boolean containsKey(Object key) {
		return this.params.containsKey(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Params) {
			Params pt = (Params) obj;
			return this.params.equals(pt.params);
		}
		return false;
	}

	public int hashCode() {
		return this.params.hashCode();
	}

	public String toString() {
		return params.toString();
	}
	
	/**
	 * 把参数包装为android-async-http框架需要的请求参数，如改用其他网络框架，另写一个包装方法即可
	 * @return
	 */
	public RequestParams convertToRequestParams() {
		RequestParams asyncHttpRequestParams = new RequestParams();
		Iterator<Entry<String, Object>> iterator = this.params.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.String, java.lang.Object> entry = iterator
					.next();
			asyncHttpRequestParams.put(entry.getKey(), entry.getValue());
		}
		return asyncHttpRequestParams;
	}
	
}
