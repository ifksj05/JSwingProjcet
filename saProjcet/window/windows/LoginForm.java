package windows;

import java.util.Vector;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseTF;
import jdbc.DbManager;
import model.UserModel;
import model.msg;

public class LoginForm extends BaseFrame {
	private BaseTF jtfId;
	private BaseTF jtfPw;
	private BaseJB jbLogin;
	private DbManager db;
	private Vector<Vector<String>> userData;
	private MainForm mainForm;

	public LoginForm(MainForm mainForm) {
		this.mainForm = mainForm;
		setFrame("로그인 폼", 250, 150);

	}

	@Override
	public void make() {
		jtfId = new BaseTF();
		jtfPw = new BaseTF();

		jbLogin = new BaseJB("로그인");

	}

	@Override
	public void design() {

		// 김 : 여백 설정 해야 함.

		jpCenter.addChild();
		jpCenter.setBorder(0, 0, 10, 0);
		jpCenter.jpCenter.setBorder(0, 10, 0, 0);
		jpCenter.jpLeft.setGrid(2, 1, 0, 10); // 김 : 여백 설정 필요
		jpCenter.jpLeft.add(new BaseJL("아이디"));
		jpCenter.jpLeft.add(new BaseJL("비밀번호"));

		jpCenter.jpCenter.setGrid(2, 1, 0, 10); // 김 : 여백 설정 필요
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);

		jpBottom.add(jbLogin);

	}

	@Override
	public void event() {

		db = new DbManager();

		jbLogin.addActionListener(e -> {

			String id = jtfId.getText();
			String pw = jtfPw.getText();
			System.out.println("입력 아이디 : " + id + " 입력 비밀번호 : " + pw);

			userData = db.getDb("SELECT * FROM sa_project.user where u_id = ? and u_pw = ?;", id, pw);

			if (userData.size() == 0) {
				msg.error("로그인 실패");
				super.dispose();

				return;
			}

			msg.info("로그인 성공");

			UserModel.u_no = userData.get(0).get(0);
			UserModel.u_id = userData.get(0).get(1);
			UserModel.u_name = userData.get(0).get(2);
			UserModel.u_pw = userData.get(0).get(3);

			UserModel.setLog(true);
			mainForm.refreshLogState();
			super.dispose();

		});
	}

}
