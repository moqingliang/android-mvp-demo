package com.ivan.mvp.app.api;

public class API {

	public static final String SERVER_IP = "http://121.201.20.67:8787";

	public static final class User {
		public static final String VO = SERVER_IP
				+ "/wap/user.txt";
		public static final String LIST = SERVER_IP
				+ "/wap/userList.txt";
		public static final String PACKAGE = SERVER_IP
				+ "/wap/userPackage.txt";
	}

}
