package dao.dml.select;

import java.util.Vector;

import jdbc.DbManager;

public class LoginDateDao {
	private DbManager db;

	Vector<Vector<String>> data = new Vector<>();

	public LoginDateDao(String[] userData) {

		db = new DbManager();
		data = db.getData(
				"SELECT * FROM ghas_notice.user where u_id = '" + userData[0] + "' and u_pw = '" + userData[1] + "';");

	}

	public Vector<Vector<String>> getData() {

		return data;
	}
}
