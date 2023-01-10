package windows;

import java.util.Calendar;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseTA;
import bases.BaseTF;
import jdbc.DbManager;
import model.UserModel;
import model.msg;

public class InsertContentsForm extends BaseFrame {
	private BaseJL jlName;
	private BaseJL jlWritingDate;
	private BaseTA jtaContents;
	private BaseJB jbSave;
	private BaseJB jbClose;
	private String today;
	private BaseTF jtfTitle;
	private DbManager db;
	private MainForm mainForm;

	public InsertContentsForm(MainForm mainForm) {
		this.mainForm = mainForm;
		setFrame("글쓰기 창", 500, 350);
	}

	@Override
	public void make() {

		Calendar date = Calendar.getInstance();

		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);

		today = year + "/" + month + "/" + day;

		jlName = new BaseJL("작성자 : " + UserModel.u_name);
		jlWritingDate = new BaseJL("작성 날짜 : " + today);
		jtfTitle = new BaseTF();
		jtaContents = new BaseTA();

		jbSave = new BaseJB("저장");
		jbClose = new BaseJB("닫기");

	}

	@Override
	public void design() {
		jpTop.addChild();
		jpTop.jpLeft.add(jlName);
		jpTop.jpRight.add(jlWritingDate);

		jpCenter.addChild();
		jpCenter.jpTop.add(jtfTitle);
		jpCenter.add(jtaContents);

		jpBottom.addChild();
		jpBottom.jpRight.setFlow();
		jpBottom.jpRight.add(jbSave);
		jpBottom.jpRight.add(jbClose);

	}

	@Override
	public void event() {
		db = new DbManager();
		jbSave.addActionListener(e -> {
			db.setDb(
					"INSERT INTO `sa_project`.`notice` (`u_no`, `n_title`, `n_contents`, `n_date`) VALUES (?, ?, ?, ?);",
					UserModel.u_no, jtfTitle.getText(), jtaContents.getText(), today);

			msg.info("글 생성이 완료 됐습니다.");
			mainForm.setContentsTable();
			super.dispose();
		});

		jbClose.addActionListener(e -> {
			super.dispose();
		});
	}

}
