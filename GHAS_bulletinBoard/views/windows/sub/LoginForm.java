package windows.sub;

import java.util.Vector;

import javax.swing.JTextField;

import comps.BaseButton;
import comps.BaseLable;
import controller.mainview.sub.LoginFormController;
import frame.BaseFrame;

public class LoginForm extends BaseFrame {

	private JTextField jtfId;
	private JTextField jtfPw;
	private BaseButton jbLogin;
	private String LoginBefore = "";
	private String[] userData = new String[2];
	private LoginFormController lfc;
	private Vector<Vector<String>> data;

	public LoginForm() {
		setFrame("로그인 폼", 300, 150);
	}

	@Override
	public void mkComp() {
		jtfId = new JTextField();
		jtfPw = new JTextField();

		jbLogin = new BaseButton("로그인");
	}

	@Override
	public void disign() {

		jpMain.setBorder(20, 20, 20, 20);

		jpLeft.setGrid(2, 1, 0, 10);
		jpLeft.add(new BaseLable("ID"));
		jpLeft.add(new BaseLable("PW"));

		jpCenter.setBorder(0, 10, 0, 10);
		jpCenter.setGrid(2, 1, 0, 10);
		jpCenter.add(jtfId);
		jpCenter.add(jtfPw);

		jpRight.add(jbLogin);

	}

	@Override
	public void event() {
		jbLogin.addActionListener(e -> {

			System.out.println(jtfId.getText() + " " + jtfPw.getText());
			userData[0] = jtfId.getText();
			userData[1] = jtfPw.getText();

			lfc = new LoginFormController(userData);

			data = lfc.getData();

			if (data.size() == 0)
				System.out.println("데이터 없음");
			else {
				System.out.println("로그인 됐습니다.");
			}

		});
	}

	public void setLoginBefore(String loginBefore) {
		LoginBefore = loginBefore;
	}

	public String getLoginBefore() {
		return LoginBefore;
	}

//	public static void main(String[] args) {
//		new LoginForm();
//	}

}
