package controller.mainview.sub;

import jdbc.DbManager;

public class SignUpContoller {
	private DbManager db;

	public SignUpContoller(String name, String id, String pw) {

		System.out.println(name + " " + id + " " + pw);

		db = new DbManager();

//		db.setData("INSERT INTO `ghas_notice`.`user` (`u_id`, `u_pw`, `name`) VALUES ('" + name + "', '" + id + "', '"
//				+ pw + "');\r\n" + "");
//
//		System.out.println("회원가입 됐습니다.");
	}

//	public void setUserData(String name, String id, String pw) {
//		this.name = name;
//		this.id = id;
//		this.pw = pw;
//	}

}
