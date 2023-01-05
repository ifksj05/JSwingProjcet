package windows;

import javax.swing.JTextField;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseLb;
import bases.BasePn;

public class SignupForm extends BaseFrame {

	public static void main(String[] args) {
		new SignupForm();
	}

	private JTextField nameTb;
	private JTextField idTb;
	private JTextField pwTb;
	private BaseBt signupBt;
	private BaseBt duplicationCheckBt;

	public SignupForm() {
		setFrame("회원가입", 300, 250);

	}

	@Override
	public void make() {
		nameTb = new JTextField();
		idTb = new JTextField();
		pwTb = new JTextField();

		duplicationCheckBt = new BaseBt("중복확인");

		signupBt = new BaseBt("회원가입");

	}

	@Override
	public void disign() {

		center.setBorder(0, 0, 10, 0);

		center.addChild();
		
		int vgap = 40;
		center.left.setGrid(3, 1, 0, vgap);
		center.left.add(new BaseLb("이름"));
		center.left.add(new BaseLb("아이디"));
		center.left.add(new BaseLb("비밀번호"));

		center.center.setBorder(0, 10, 0, 10);
		
		center.center.setGrid(3, 1, 0, vgap);
		center.center.add(nameTb);
		center.center.add(idTb);
		center.center.add(pwTb);

		center.right.setGrid(3, 1, 0, vgap);
		center.right.add(new BaseLb(""));
		center.right.add(duplicationCheckBt);
		center.right.add(new BaseLb(""));

		bottom.add(signupBt);

	}

	@Override
	public void event() {

	}

}
