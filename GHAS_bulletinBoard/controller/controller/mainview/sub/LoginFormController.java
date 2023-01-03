package controller.mainview.sub;

import java.util.Vector;

import dao.dml.select.LoginDateDao;
import jdbc.DbManager;
import windows.sub.LoginForm;

public class LoginFormController {
	private String[] userData;
	private LoginForm LoginForm;
	private LoginDateDao ldd;
	private Vector<Vector<String>> data;

	public LoginFormController(String[] userData) {
		this.userData = userData;

		ldd = new LoginDateDao(userData);

		data = ldd.getData();

	}

	public Vector<Vector<String>> getData() {
		return data;
	}

}
