package windows;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseI;
import bases.BaseLb;
import jdbc.DbManager;

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
		main.setBorder(10, 10, 10, 10);

		// top
		top.addChild();
		top.setBorder(0, 0, 10, 0);

		top.top.add(new BaseLb("GHAS 게시판").setCenter().setFont(30));

		top.bottom.addChild();

		top.bottom.center.add(wellcomeLb.setCenter());

		top.bottom.right.setGrid(2, 1, 0, 10);
		top.bottom.right.add(loginBt);
		top.bottom.right.add(signupBt);

		// center
		center.add(noticeTableScrollPn);
		System.out.println(noticTableData.get(0).get(0));

	}

	@Override
	public void event() {

	}

}
