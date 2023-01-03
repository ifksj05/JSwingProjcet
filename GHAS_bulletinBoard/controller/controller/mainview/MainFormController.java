package controller.mainview;

import windows.MainFrom;
import windows.sub.LoginForm;

public class MainFormController {
	public MainFormController(MainFrom mainFrom) {

		new LoginForm(mainFrom);
		

	}
}
