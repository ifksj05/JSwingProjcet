package windows;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseI;
import bases.BaseLb;
import jdbc.DbManager;
import res.ResManager;

public class MainForm extends BaseFrame {

	private BaseBt loginBt;
	private BaseBt signupBt;
	private BaseBt logoutBt;
	private BaseLb wellcomeLb;
	private DbManager db;
	private Vector<Vector<String>> noticTableData;
	private JScrollPane noticeTableScrollPn;
	private Vector<String> cols;
	private DefaultTableModel noticTableDtm;
	private JTable noticeTable;
	private BaseBt writingBt;

	public MainForm() {
		setFrame("게시판", 500, 500);
	}

	@Override
	public void make() {
		// 상단
		wellcomeLb = new BaseLb("wellcomeLb");

		loginBt = new BaseBt("로그인");
		signupBt = new BaseBt("회원가입");

		logoutBt = new BaseBt("로그아웃");
		writingBt = new BaseBt("글쓰기");

		// 센터
		cols = new Vector<String>();
		cols.add("번호");
		cols.add("회원번호");
		cols.add("제목");
		cols.add("내용");
		cols.add("날짜");

		db = new DbManager();
		noticTableData = db.getData("SELECT * FROM ghas_notice.notice;");

		noticTableDtm = new DefaultTableModel(noticTableData, cols);
		noticeTable = new JTable(noticTableDtm);
		noticeTableScrollPn = new JScrollPane(noticeTable);

	}

	@Override
	public void disign() {
		// main
//		main.setBorder(10, 10, 10, 10);

		// top
		top.addChild();
		top.setBorder(0, 0, 10, 0);

		top.top.add(new BaseLb("GHAS 게시판").setCenter().setFont(30));

		top.bottom.addChild();

		top.bottom.center.add(wellcomeLb.setCenter().setFont(15));

		top.bottom.right.setGrid(2, 1, 0, 10);
		statusIsNotLogin();

		// center
		center.add(noticeTableScrollPn);
		System.out.println(noticTableData.get(0).get(0));

	}

	@Override
	public void event() {
		
		tableEvent();
		
		loginBt.addActionListener(e -> {

			new LoginForm(this);

		});

		signupBt.addActionListener(e -> {

			new SignupForm();

		});

		logoutBt.addActionListener(e -> {
			statusIsNotLogin();

		});

		writingBt.addActionListener(e -> {
			new WitingForm(this);
		});

	}

	private void tableEvent() {
		// TODO Auto-generated method stub
		MainForm mainFrame = this;

		noticeTable.addMouseListener(new MouseAdapter() {

			private Vector<String> contentsData;

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(noticeTable.getSelectedRow() + "번 클릭");
				contentsData = noticTableData.get(noticeTable.getSelectedRow());

				System.out.println(contentsData.get(0));

				new ContentForm(contentsData, mainFrame);

//				String cleckNum = (String) noticeTable.getValueAt(noticeTable.getSelectedRow(), 0);
//				System.out.println(cleckNum + ": 번호 값");

			}

		});
	}

	public void statusIsNotLogin() {
		wellcomeLb.setText("환영합니다 로그인 하세요.");

		ResManager.setUserDataNull();

		top.bottom.right.removeAll();
		top.bottom.right.add(loginBt);
		top.bottom.right.add(signupBt);
		repaint();

	}

	public void statusIsLogin() {
		wellcomeLb.setText(ResManager.userName + "님 환영합니다.");

		top.bottom.right.removeAll();
		top.bottom.right.add(logoutBt);
		top.bottom.right.add(writingBt);

		repaint();
	}

	public void changeTable() {
		noticTableData = db.getData("SELECT * FROM ghas_notice.notice;");
		noticTableDtm = new DefaultTableModel(noticTableData, cols);
		noticeTable = new JTable(noticTableDtm);
		noticeTableScrollPn = new JScrollPane(noticeTable);

		center.removeAll();
		center.add(noticeTableScrollPn);

		tableEvent();
		repaint();
	}

}
