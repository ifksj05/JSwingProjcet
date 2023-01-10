package windows;

import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;

public class MainForm extends BaseFrame {

	private BaseJB jbLogin;
	private BaseJB jbSignup;
	private BaseJL jlWellcome;
	private JTable jtNotice;
	private Vector<Vector<String>> dataNotice;
	private Vector<String> colsNotice;
	private DefaultTableModel dtmNotice;
	private JScrollPane jspNotice;

	public MainForm() {
		setFrame("메인 창", 500, 500);
	}

	@Override
	public void make() {

		jbLogin = new BaseJB("로그인");
		jbSignup = new BaseJB("회원가입");

		jlWellcome = new BaseJL("로그인 하세요");

		colsNotice = new Vector<String>();
		colsNotice.add("번호");
		colsNotice.add("닉네임");
		colsNotice.add("제목");
		colsNotice.add("내용");
		colsNotice.add("날짜");

		// event 데이터 셋팅 해야 함.
		dataNotice = new Vector<Vector<String>>();

		dtmNotice = new DefaultTableModel(dataNotice, colsNotice);
		jtNotice = new JTable(dtmNotice);
		jspNotice = new JScrollPane(jtNotice);
	}

	@Override
	public void design() {

		// 상단
		jpTop.addChild();

		jpTop.jpCenter.addChild();

		jpTop.jpCenter.jpCenter.add(new BaseJL("GHAS 게시판"));

		jpTop.jpCenter.jpRight.setGrid(2, 1, 0, 0); // 보더 크기 설정 필요
		jpTop.jpCenter.jpRight.add(jbLogin);
		jpTop.jpCenter.jpRight.add(jbSignup);

		jpTop.jpBottom.add(jlWellcome);

		// 센털
		jpCenter.add(jspNotice);

	}

	@Override
	public void event() {

	}

}
