package windows;

import java.awt.Component;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseLb;
import jdbc.DbManager;
import res.ResManager;

public class WitingForm extends BaseFrame {

//	public static void main(String[] args) {
//		new WitingForm();
//	}

	private JTextField titleTf;
	private JTextArea contentsTf;
	private BaseBt saveBt;
	private DbManager db;
	private MainForm mainForm;
	private Vector<String> contentsData;
	private int statusForm = 0;
	private ContentForm contentForm;

	public WitingForm(MainForm mainForm) {
		statusForm = 1;
		this.mainForm = mainForm;
		setFrame("글쓰기", 400, 500);
	}

	public WitingForm(ContentForm contentForm, MainForm mainForm, String string, Vector<String> contentsData) {
		statusForm = 2;
		this.contentForm = contentForm;
		this.mainForm = mainForm;
		this.contentsData = contentsData;
		setFrame(string, 500, 500);
		titleTf.setText(contentsData.get(2));
		contentsTf.setText(contentsData.get(3));

	}

	@Override
	public void make() {
		titleTf = new JTextField();
		contentsTf = new JTextArea(); // 피드백 필요 내용 텍필 뭘로 해야 하죠 ?

		saveBt = new BaseBt("저장");

	}

	@Override
	public void disign() {
		top.addChild();
		top.left.add(new BaseLb("제목 : ").setFont(20));
		top.center.add(titleTf);

		top.bottom.setBorder(20, 0, 0, 0);
		top.bottom.addChild();
		top.bottom.left.add(new BaseLb("작성자 : " + ResManager.userName).setFont(15));

		center.add(contentsTf);

		bottom.setFlow();
		bottom.add(saveBt);

	}

	@Override
	public void event() {

		db = new DbManager();
		Calendar time = Calendar.getInstance();
		
		
		
		saveBt.addActionListener(e -> {

			if (statusForm == 1) { // 글쓰기

				System.out.println("글 쓰기 이벤트 실행");
				int year = time.get(Calendar.YEAR);
				int month = time.get(Calendar.MONTH) + 1;
				int day = time.get(Calendar.DAY_OF_MONTH);

				String title = titleTf.getText();
				String contents = contentsTf.getText();
				String date = year + "-" + month + "-" + day;

				System.out.println(date);

				db.setData(
						"INSERT INTO `ghas_notice`.`notice` (`u_no`, `n_title`, `n_contents`, `n_mkdate`) VALUES (?, ?, ?, ?);",
						ResManager.userNo, title, contents, date);

//				((BaseFrame) mainForm).repaint();

//				왜 새로고침이 안돼죠 ?? ㅜㅜ 
				statusForm = 0;
				mainForm.changeTable();
				super.dispose();
			} else if (statusForm == 2) { // 글 수정
				System.out.println("글 수정 이벤트 실행");

				String title = titleTf.getText();
				String contents = contentsTf.getText();

				db.setData("UPDATE `ghas_notice`.`notice` SET `n_title` = ?, `n_contents` = ? WHERE (`n_no` = ?);",
						title, contents, contentsData.get(0));

				statusForm = 0;
				mainForm.changeTable();
				contentForm.refresh();
				super.dispose();
			}

		});
	}

}
