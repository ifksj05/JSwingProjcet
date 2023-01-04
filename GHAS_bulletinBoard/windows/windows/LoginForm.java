package windows;

import javax.swing.JTextField;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseLb;

public class LoginForm extends BaseFrame {

//	public static void main(String[] args) {
//		new LoginForm();
//	}

	private JTextField idTf;
	private JTextField pwTf;
	private BaseBt loginBt;

	public LoginForm() {
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

		main.setBorder(10, 10, 10, 10);

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

	}

}
