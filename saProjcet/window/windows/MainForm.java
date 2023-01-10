package windows;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseJP;
import jdbc.DbManager;
import res.UserModel;

public class MainForm extends BaseFrame {

	private BaseJB jbLogin;
	private BaseJB jbSignup;
	private BaseJL jlWellcome;
	private JTable jtNotice;
	private Vector<Vector<String>> dataNotice;
	private Vector<String> colsNotice;
	private DefaultTableModel dtmNotice;
	private JScrollPane jspNotice;
	private DbManager db;
	private BaseJP jpstateLogin;
	private BaseJP jpstateLogout;
	private BaseJB jbLogout;
	private BaseJB jbWriting;

	public MainForm() {
		setFrame("메인 창", 500, 350);
	}

	@Override
	public void make() {

		db = new DbManager();

		jbLogin = new BaseJB("로그인");
		jbSignup = new BaseJB("회원가입");
		jpstateLogout = new BaseJP();
		jpstateLogout.setGrid(2, 1, 0, 0);
		jpstateLogout.add(jbLogin);
		jpstateLogout.add(jbSignup);

		jbLogout = new BaseJB("로그아웃");
		jbWriting = new BaseJB("글쓰기");
		jpstateLogin = new BaseJP();
		jpstateLogin.setGrid(2, 1, 0, 0);
		jpstateLogin.add(jbLogout);
		jpstateLogin.add(jbWriting);

		jlWellcome = new BaseJL("로그인 하세요");

		colsNotice = new Vector<String>();
		colsNotice.add("번호");
		colsNotice.add("닉네임");
		colsNotice.add("제목");
		colsNotice.add("내용");
		colsNotice.add("날짜");

		dataNotice = new Vector<Vector<String>>();
		dataNotice = db.getDb("SELECT * FROM sa_project.notice;");

		dtmNotice = new DefaultTableModel(dataNotice, colsNotice);
		jtNotice = new JTable(dtmNotice);
		jspNotice = new JScrollPane(jtNotice);
	}

	@Override
	public void design() {

		// 김 : 여백 설정 해야 함.

		// 상단
		jpTop.addChild();

		jpTop.jpCenter.addChild();

		jpTop.jpCenter.jpCenter.add(new BaseJL("GHAS 게시판"));

		UserModel.loginState = false;
		refreshLogState();

		jpTop.jpBottom.add(jlWellcome);

		// 센털
		jpCenter.add(jspNotice);

	}

	@Override
	public void event() {
		jbLogin.addActionListener(e -> {
			new LoginForm();
		});

		jbSignup.addActionListener(e -> {
			new SignupForm();
		});

		jtNotice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (UserModel.loginState) {
					new UpdateContentsForm();
					return;
				}

				new SelectContentsForm();
			}
		});

		jbLogout.addActionListener(e -> {
			UserModel.setLogin(false);
			refreshLogState();
		});

		jbWriting.addActionListener(e -> {
			new InsertContentsForm();
		});

	}

	public void refreshLogState() {

		// 로그인
		if (UserModel.loginState) {
			jpTop.jpCenter.jpRight.removeAll();
			jpTop.jpCenter.jpRight.add(jpstateLogin);
			jlWellcome.setText("님 환영합니다.");
			super.refresh();

			return;
		}

		// 로그아웃
		jpTop.jpCenter.jpRight.removeAll();
		jpTop.jpCenter.jpRight.add(jpstateLogout);
		jlWellcome.setText("로그인 하세요");
		super.refresh();

	}

}
