package windows;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseJP;
import jdbc.DbManager;
import model.UserModel;

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
	private MainForm mainForm;

	public MainForm() {
		setFrame("메인 창", 500, 350);
	}

	@Override
	public void make() {

		db = new DbManager();

		jbLogin = new BaseJB("로그인");
		jbSignup = new BaseJB("회원가입");
		jpstateLogout = new BaseJP();
		jpstateLogout.setGrid(2, 1, 0, 5);
		jpstateLogout.add(jbLogin);
		jpstateLogout.add(jbSignup);

		jbLogout = new BaseJB("로그아웃");
		jbWriting = new BaseJB("글쓰기");
		jpstateLogin = new BaseJP();
		jpstateLogin.setGrid(2, 1, 0, 5);
		jpstateLogin.add(jbLogout);
		jpstateLogin.add(jbWriting);

		jlWellcome = new BaseJL("로그인 하세요").setFont(15);

	}

	@Override
	public void design() {

		// 김 : 여백 설정 해야 함.

		// 상단
		jpTop.addChild();

		jpTop.jpCenter.addChild();

		jpTop.jpCenter.jpCenter.add(new BaseJL("GHAS 게시판").setFont(25));

		UserModel.loginState = false;
		refreshLogState();

		jpTop.jpBottom.add(jlWellcome);

		// 센털
		setContentsTable();

	}

	@Override
	public void event() {

		mainForm = this;

		jbLogin.addActionListener(e -> {
			new LoginForm(this);
		});

		jbSignup.addActionListener(e -> {
			new SignupForm();
		});

		jbLogout.addActionListener(e -> {
			UserModel.setLog(false);
			refreshLogState();
		});

		jbWriting.addActionListener(e -> {
			new InsertContentsForm(this);
		});

	}

	public void setContentsTable() {
		jpCenter.removeAll();

		colsNotice = new Vector<String>();
		colsNotice.add("번호");
		colsNotice.add("닉네임");
		colsNotice.add("제목");
		colsNotice.add("내용");
		colsNotice.add("날짜");

		dataNotice = new Vector<Vector<String>>();
		dataNotice = db.getDb("SELECT n_no, u_name, n_title, n_contents, n_date FROM user join notice\r\n"
				+ "	on user.u_no = notice.u_no\r\n" + ";");

		dtmNotice = new DefaultTableModel(dataNotice, colsNotice);
		jtNotice = new JTable(dtmNotice);
		jspNotice = new JScrollPane(jtNotice);

		jpCenter.add(jspNotice);

		jtNotice.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				Vector<String> tmp = new Vector<>();

				System.out.println(jtNotice.getSelectedRow());
//				System.out.println("번호는 " + jtNotice.getValueAt(jtNotice.getRowCount() - 1, 0));

				for (int i = 0; i < 5; i++) {
					tmp.add((String) jtNotice.getValueAt(jtNotice.getSelectedRow(), i));
					System.out.println(tmp.get(i));
				}

				if (UserModel.loginState) {
					new UpdateContentsForm(tmp, mainForm);
					return;
				}

				new SelectContentsForm(tmp);
			}
		});

		super.refresh();
	}

	public void refreshLogState() {

		// 로그인
		if (UserModel.loginState) {
			jpTop.jpCenter.jpRight.removeAll();
			jpTop.jpCenter.jpRight.add(jpstateLogin);
			jlWellcome.setText(UserModel.u_name + "님 환영합니다.");
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
