package windows;

import java.util.Vector;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseTF;
import jdbc.DbManager;
import model.msg;

public class SignupForm extends BaseFrame {
	private BaseTF jtfId;
	private BaseTF jtfName;
	private BaseTF jtfPw;
	private BaseTF jtfPwCheck;
	private BaseJB jbSignup;
	private DbManager db;

	public SignupForm() {
		setFrame("회원가입 폼", 250, 400);
	}

	@Override
	public void make() {
		jtfId = new BaseTF();
		jtfName = new BaseTF();
		jtfPw = new BaseTF();
		jtfPwCheck = new BaseTF();

		jbSignup = new BaseJB("회원가입");

	}

	@Override
	public void design() {

		// 김 : 여백 설정 필요 ( 판낼, 그리드 )

		jpCenter.addChild();

		jpCenter.jpLeft.setGrid(4, 1, 0, 0);
		jpCenter.jpLeft.add(new BaseJL("아이디"));
		jpCenter.jpLeft.add(new BaseJL("닉네임"));
		jpCenter.jpLeft.add(new BaseJL("비밀번호"));
		jpCenter.jpLeft.add(new BaseJL("비밀번호 확인"));

		jpCenter.jpCenter.setGrid(4, 1, 0, 0);
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfName);
		jpCenter.jpCenter.add(jtfPw);
		jpCenter.jpCenter.add(jtfPwCheck);

		jpCenter.jpBottom.add(jbSignup);

	}

	@Override
	public void event() {

		db = new DbManager();

		jbSignup.addActionListener(e -> {

			String id = jtfId.getText();
			String name = jtfName.getText();
			String pw = jtfPw.getText();
			String pwch = jtfPwCheck.getText();

			System.out.println("id : " + id + " name : " + name + " pw : " + pw + " pwch : " + pwch);

			Vector<Vector<String>> idData = db.getDb("SELECT * FROM sa_project.user where u_id = ?;", id);
			Vector<Vector<String>> nameData = db.getDb("SELECT * FROM sa_project.user where u_name = ?;", name);

			if (id.isBlank() || name.isBlank() || pw.isBlank() || pwch.isBlank()) {
				msg.error("빈칸이 존재함.");
				if (id.isBlank()) {
					jtfId.requestFocus();
					return;
				}
				if (name.isBlank()) {
					jtfName.requestFocus();
					return;
				}
				if (pw.isBlank()) {
					jtfPw.requestFocus();
					return;
				}
				if (pwch.isBlank()) {
					jtfPwCheck.requestFocus();
					return;

				}

			}
			if (idData.size() != 0) {
				msg.error("id가 존재함.");
				jtfId.setText("");
				jtfId.requestFocus();
				return;
			}
			if (nameData.size() != 0) {
				msg.error("name가 존재함.");
				jtfName.setText("");
				jtfName.requestFocus();
				return;
			}

			if (!pw.equals(pwch)) {
				msg.error("pw가 다름.");
				jtfPw.setText("");
				jtfPwCheck.setText("");
				jtfPw.requestFocus();
				return;
			}

			msg.info("회원가입 성공, 와 미쳤다 !!");
			db.setDb("INSERT INTO `sa_project`.`user` (`u_id`, `u_name`, `u_pw`) VALUES (?, ?, ?);", id, name, pw);
			super.dispose();
		});
	}

}
