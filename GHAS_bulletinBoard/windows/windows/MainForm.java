package windows;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseI;
import bases.BaseLb;

public class MainForm extends BaseFrame {

	private BaseBt loginBt;
	private BaseBt signupBt;
	private BaseLb wellcomeLb;

	public MainForm() {
		setFrame("게시판", 500, 500);
	}

	@Override
	public void make() {
		wellcomeLb = new BaseLb("wellcomeLb");

		loginBt = new BaseBt("로그인");
		signupBt = new BaseBt("회원가입");

	}

	@Override
	public void disign() {
		// main
		main.setBorder(10, 10, 10, 10);

		// top
		top.addChild();

		top.top.add(new BaseLb("GHAS 게시판").setCenter().setFont(30));

		top.bottom.addChild();

		top.bottom.center.add(wellcomeLb.setCenter());

		top.bottom.right.setGrid(2, 1, 0, 10);
		top.bottom.right.add(loginBt);
		top.bottom.right.add(signupBt);

		// center

	}

	@Override
	public void event() {

	}

}
