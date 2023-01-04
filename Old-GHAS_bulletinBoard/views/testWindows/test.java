package testWindows;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import frame.BaseFrame;
import jdbc.DbManager;

public class test extends BaseFrame {

	public static void main(String[] args) {
		new test();
	}

	private Vector<String> cols;
	private DbManager db;
	private DefaultTableModel dtm;
	private JTable jT;
	private JScrollPane scroll;
	Vector<Vector<String>> data;

	public test() {
		setFrame("test", 500, 500);
	}

	@Override
	public void mkComp() {

		// table 컬럼 생성
		cols = new Vector<String>();
		cols.add("번호");
		cols.add("제목");
		cols.add("작성일");
		cols.add("컬럼");

		// table 데이터 생성

		db = new DbManager();
		data = db.getData("SELECT n_no, n_title, n_mkdate, n_contents FROM ghas_notice.notice;");

		// jtable 생성
		dtm = new DefaultTableModel(data, cols);
		jT = new JTable(dtm);
		scroll = new JScrollPane(jT);

	}

	@Override
	public void disign() {
		jpCenter.add(scroll);
	}

	@Override
	public void event() {
		jT.addMouseListener(new MouseAdapter() {
			private Vector<Vector<String>> contents;

			@Override
			public void mouseClicked(MouseEvent e) {
				
				Vector<String> row = new Vector<String>();
				row.add("f");
				row.add("a");
				row.add("v");
				row.add("xx");
				
				data.set(0, row);
				
//				int row = jT.getSelectedRow();
//				System.out.println(data.get(0).size());
//				System.out.println(data.get(row).get(3));
//								new testSub(data.get(row));

				//////////////////// 2
				// System.out.println("test1");
//				System.out.println(jT.getSelectedRow());
////				int tmp = jT.getSelectedRow() + 1;
//				String tmp = (String) jT.getValueAt(jT.getSelectedRow(), 0);
//
//				System.out.println(tmp);
//
//				contents = db.getData("SELECT n_no, n_contents FROM ghas_notice.notice where n_no = " + tmp + ";");
//
//				System.out.println(contents.size() + "아무내용");
//
//				System.out.println(contents);
//				System.out.println(contents.get(0).get(1));
//				new testSub(contents);

				super.mouseClicked(e);
			}

		});

	}

}