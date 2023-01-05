package windows;

import java.awt.Component;
import java.util.Calendar;

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
	private JTextField contentsTf;
	private BaseBt saveBt;
	private DbManager db;
	private MainForm mainForm;

	public WitingForm(MainForm mainForm) {
		this.mainForm = mainForm;
		setFrame("글쓰기", 400, 500);
	}

	@Override
	public void make() {
		titleTf = new JTextField();
		contentsTf = new JTextField(); // 피드백 필요 내용 텍필 뭘로 해야 하죠 ?

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

//			((BaseFrame) mainForm).repaint();

//			왜 새로고침이 안돼죠 ?? ㅜㅜ 

			mainForm.changeTable();
			super.dispose();

		});
	}

}
