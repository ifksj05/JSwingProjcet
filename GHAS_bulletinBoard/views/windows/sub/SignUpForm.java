package windows.sub;

import javax.swing.JTextField;

import comps.BaseButton;
import comps.BaseLable;
import frame.BaseFrame;

public class SignUpForm extends BaseFrame {
	public static void main(String[] args) {
		new SignUpForm();
	}

	private JTextField jtfName;
	private JTextField jtfId;
	private JTextField jtfPw;
	private BaseButton jbtSignup;

	public SignUpForm() {
		setFrame("회원가입", 300, 250);
	}

	@Override
	public void mkComp() {
		jtfName = new JTextField();
		jtfId = new JTextField();
		jtfPw = new JTextField();
		jbtSignup = new BaseButton("회원가입");
	
	}

	@Override
	public void disign() {
		
		jpMain.setBorder(20, 20, 20, 20);
		
		jpCenter.addChild();
		jpCenter.setBorder(0, 0, 20, 0);
		
		jpCenter.jpLeft.setGrid(3, 1, 0, 20);
		jpCenter.jpLeft.setBorder(0, 0, 0, 10);
		jpCenter.jpLeft.add(new BaseLable("이름"));
		jpCenter.jpLeft.add(new BaseLable("아이디"));
		jpCenter.jpLeft.add(new BaseLable("비밀번호"));

		jpCenter.jpCenter.setGrid(3, 1, 0, 20);
		jpCenter.jpCenter.add(jtfName);
		jpCenter.jpCenter.add(jtfId);
		jpCenter.jpCenter.add(jtfPw);
		
		jpBottom.add(jbtSignup);

	}

	@Override
	public void event() {

	}

}
