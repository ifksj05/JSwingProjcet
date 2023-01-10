package windows;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseTF;

public class LoginForm extends BaseFrame {
	private BaseTF jtfId;
	private BaseTF jtfPw;
	private BaseJB jbLogin;

	public LoginForm() {

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

		jpCenter.jpLeft.setGrid(2, 1, 0, 0); // 김 : 여백 설정 필요
		jpCenter.jpLeft.add(new BaseJL("아이디"));
		jpCenter.jpLeft.add(new BaseJL("비밀번호"));

		jpCenter.jpCenter.setGrid(2, 1, 0, 0); // 김 : 여백 설정 필요
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);

		jpBottom.add(jbLogin);

	}

	@Override
	public void event() {
		jbLogin.addActionListener(e -> {
			super.dispose();
		});
	}

}
