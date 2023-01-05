package windows;

import java.util.Vector;

import javax.swing.JTextField;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseLb;
import jdbc.DbManager;

public class SignupForm extends BaseFrame {

	public static void main(String[] args) {
		new SignupForm();
	}

	private JTextField nameTb;
	private JTextField idTb;
	private JTextField pwTb;
	private BaseBt signupBt;
	private BaseBt idCheckBt;
	private String inputId;
	private String inputName;
	private String inputPw;
	private DbManager db;
	private Vector<Vector<String>> idCheckData;
	private boolean idCheckOkey;

	public SignupForm() {
		setFrame("회원가입", 300, 200);

	}

	@Override
	public void make() {
		nameTb = new JTextField();
		idTb = new JTextField();
		pwTb = new JTextField();

		idCheckBt = new BaseBt("중복확인");

		signupBt = new BaseBt("회원가입");

	}

	@Override
	public void disign() {

		center.setBorder(0, 0, 10, 0);

		center.addChild();

		int vgap = 10;
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
		center.right.add(idCheckBt);
		center.right.add(new BaseLb(""));

		bottom.add(signupBt);

	}

	@Override
	public void event() {
		db = new DbManager();

		idCheckBt.addActionListener(e -> {

			inputId = idTb.getText();
			idCheckData = db.getData("SELECT * FROM ghas_notice.user where u_id = ?;", inputId);

			if (idCheckData.size() != 0) {
				System.out.println("ID가 이미 존재합니다.");
				idTb.setText("");

				return;

			}

			System.out.println("중복확인이 완료됐습니다.");
			idCheckOkey = true;

		});

		signupBt.addActionListener(e -> {
			inputName = nameTb.getText();
			inputId = idTb.getText();
			inputPw = pwTb.getText();

			// 텍필 입력 안할 시
			if (inputName.isBlank() || inputId.isBlank() || inputPw.isBlank()) {
				System.out.println("빈칸이 존재합니다.");

				nameTb.setText("");
				idTb.setText("");
				pwTb.setText("");

				return;

			}

			// 중복확인 안될 시
			if (idCheckOkey == false) {
				System.out.println("중복확인을 해주세요.");
				return;
			}

			db.setData("INSERT INTO `ghas_notice`.`user` (`u_id`, `u_pw`, `name`) VALUES (?, ?, ?);", inputName,
					inputId, inputPw);
			idCheckOkey = false;

			super.dispose();

		});
	}

}
