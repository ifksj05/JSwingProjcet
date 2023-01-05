package windows;

import java.util.Vector;

import javax.swing.JTextField;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseLb;
import jdbc.DbManager;
import res.ResManager;

public class LoginForm extends BaseFrame {

//	public static void main(String[] args) {
//		new LoginForm();
//	}

	private JTextField idTf;
	private JTextField pwTf;
	private BaseBt loginBt;
	private Vector<Vector<String>> userDataTable;
	private MainForm mainForm;

	public LoginForm() {
	}

	public LoginForm(MainForm mainForm) {
		// TODO Auto-generated constructor stub
		this.mainForm = mainForm;
		setFrame("로그인", 300, 125);
	}

	@Override
	public void make() {
		idTf = new JTextField();
		pwTf = new JTextField();

		loginBt = new BaseBt("로그인");
	}

	@Override
	public void disign() {

//		main.setBorder(10, 10, 10, 10);

		left.setGrid(2, 1, 0, 10);
		left.add(new BaseLb("아이디"));
		left.add(new BaseLb("비밀번호"));

		center.setGrid(2, 1, 0, 10);
		center.setBorder(0, 10, 0, 10);
		center.add(idTf);
		center.add(pwTf);

		right.add(loginBt);

	}

	@Override
	public void event() {
		DbManager db = new DbManager();

		loginBt.addActionListener(e -> {
			ResManager.userId = idTf.getText();
			ResManager.userPw = idTf.getText();

			if (ResManager.userId.isBlank() || ResManager.userPw.isBlank()) {
				System.out.println("id 또는 pw를 입력하시오");

				idTf.setText("");
				pwTf.setText("");

				ResManager.setUserDataNull();

				return;
			}

			userDataTable = db.getData("SELECT * FROM ghas_notice.user where u_id = ? and u_pw = ?;", ResManager.userId,
					ResManager.userPw);

			if (userDataTable.size() == 0) {

				System.out.println("계정 없음");

				idTf.setText("");
				pwTf.setText("");

				ResManager.setUserDataNull();

				return;
			}

			ResManager.userNo = userDataTable.get(0).get(0);
			ResManager.userId = userDataTable.get(0).get(1);
			ResManager.userPw = userDataTable.get(0).get(2);
			ResManager.userName = userDataTable.get(0).get(3);

			mainForm.statusIsLogin();

			System.out.println("로그인 성공");

			super.dispose();

		});

	}

}
