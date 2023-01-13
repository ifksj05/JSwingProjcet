package windows;

import java.util.Vector;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseTA;
import jdbc.DbManager;
import model.UserModel;
import model.msg;

public class UpdateContentsForm extends BaseFrame {
	private BaseJL jlName;
	private BaseJL jlWritingDate;
	private BaseTA jtaContents;
	private BaseJB jbUpdate;
	private BaseJB jbDelete;
	private BaseJB jbClose;
	private Vector<String> contentsArr;
	private DbManager db;
	private MainForm mainForm;

	public UpdateContentsForm(Vector<String> contentsArr, MainForm mainForm) {
		this.contentsArr = contentsArr;
		this.mainForm = mainForm;
		setFrame("수정, 삭제창", 500, 350);
	}

	@Override
	public void make() {
		jlName = new BaseJL("작성자 : " + contentsArr.get(1)).setFont(15);
		jlWritingDate = new BaseJL("작성 날짜 : " + contentsArr.get(4)).setFont(15);
		jtaContents = new BaseTA();
		jtaContents.setText(contentsArr.get(3));

		jbUpdate = new BaseJB("수정");
		jbDelete = new BaseJB("삭제");
		jbClose = new BaseJB("닫기");

	}

	@Override
	public void design() {
		jpTop.addChild();
		jpTop.jpLeft.add(jlName);
		jpTop.jpRight.add(jlWritingDate);

		jpCenter.add(jtaContents);

		jpBottom.addChild();
		jpBottom.jpRight.setFlow();
		jpBottom.jpRight.add(jbUpdate);
		jpBottom.jpRight.add(jbDelete);
		jpBottom.jpRight.add(jbClose);

	}

	@Override
	public void event() {
		db = new DbManager();
		
		// * 만약 로그인 한 계정이 타 계정 이라면 *
		if (!contentsArr.get(1).equals(UserModel.u_name)) {
			jtaContents.setEnabled(false);
			jbUpdate.setEnabled(false);
			jbDelete.setEnabled(false);
		}
		
		jbUpdate.addActionListener(e -> {
			String contents = jtaContents.getText();
			String n_no = contentsArr.get(0);
			db.setDb("UPDATE `sa_projectㄹ`.`notice` SET `n_contents` = ? WHERE (`n_no` = ?);", contents, n_no);

			msg.info("수정 성공");
			mainForm.setContentsTable();
			super.dispose();

		});

		jbDelete.addActionListener(e -> {
			String n_no = contentsArr.get(0);
			db.setDb("DELETE FROM `sa_project`.`notice` WHERE (`n_no` = ?);", n_no);

			msg.info("삭제 성공");
			mainForm.setContentsTable();
			super.dispose();

		});

		jbClose.addActionListener(e -> {
			super.dispose();
		});
	}

}
