import java.awt.BorderLayout;
import java.awt.PopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class StudyJTable extends JFrame {

	public static void main(String[] args) {
		new StudyJTable();
	}

	private JTable table;
	private JScrollPane scrollPane;
	private Vector<Vector<String>> data;
	private Vector<String> cols;
	private DefaultTableModel dtm;
	private Vector<String> row1;
	private Vector<String> row2;
	private PopupMenu pop;

	public StudyJTable() {

		super.setTitle("테이블 연습");
		super.setSize(500, 500);

		make();
		design();
		event();

		super.setLocationRelativeTo(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	private void make() {

		data = new Vector<Vector<String>>();

		row1 = new Vector<String>();
		row1.add("김성재");
		row1.add("19");
		row1.add("경기도");

		row2 = new Vector<String>();
		row2.add("김성민");
		row2.add("16");
		row2.add("경기도");

		data.add(row1);
		data.add(row2);

		cols = new Vector<String>();
		cols.add("이름");
		cols.add("나이");
		cols.add("사는 곳");

		dtm = new DefaultTableModel(data, cols);
		table = new JTable(dtm);
		scrollPane = new JScrollPane(table);

	}

	private void design() {
		super.add(scrollPane, BorderLayout.CENTER);
	}

	private void event() {

		pop = new PopupMenu();
		pop.add("수정");
		pop.add("삭제");

		JFrame jf = this;
		table.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				if (SwingUtilities.isRightMouseButton(e)) {

					pop.show(jf, 1, 1);
				}

			}

		});
	}

}
