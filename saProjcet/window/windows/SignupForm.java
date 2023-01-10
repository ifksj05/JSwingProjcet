package windows;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseTF;

public class SignupForm extends BaseFrame {
	private BaseTF jtfId;
	private BaseTF jtfName;
	private BaseTF jtfPw;
	private BaseTF jtfPwCheck;
	private BaseJB jbSignup;

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
		jbSignup.addActionListener(e -> {
			super.dispose();
		});
	}

}
