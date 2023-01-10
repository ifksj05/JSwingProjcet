package model;

public class UserModel {
	public static boolean loginState; // 로그인 true 로그아웃 false
	public static String u_no;
	public static String u_id;
	public static String u_name;
	public static String u_pw;

	public static void setLog(boolean logState) {
		UserModel.loginState = logState;
		
		if (!logState) {
			u_no = null;
			u_id = null;
			u_name = null;
			u_pw = null;
		}
	}

}
