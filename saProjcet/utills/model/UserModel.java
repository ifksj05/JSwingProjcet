package model;

public class UserModel {
	public static boolean loginState; // 로그인 true 로그아웃 false

	public static void setLogin(boolean logState) {
		UserModel.loginState = logState;
	}

}
