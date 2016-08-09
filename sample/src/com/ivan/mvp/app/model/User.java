package com.ivan.mvp.app.model;

import com.ivan.mvp.base.model.Model;

public class User extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String name;

	public int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "name=" + name + ", age=" + age;
	}

}
