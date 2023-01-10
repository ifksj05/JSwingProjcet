package windows;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseTA;

public class UpdateContentsForm extends BaseFrame {
	private BaseJL jlName;
	private BaseJL jlWritingDate;
	private BaseTA jtfContents;
	private BaseJB jbUpdate;
	private BaseJB jbDelete;
	private BaseJB jbClose;

	public UpdateContentsForm() {
		setFrame("수정, 삭제창", 500, 350);
	}

	@Override
	public void make() {
		jlName = new BaseJL("작성자 : name");
		jlWritingDate = new BaseJL("작성 날짜 : yyyy-mm-dd");
		jtfContents = new BaseTA();

		jbUpdate = new BaseJB("수정");
		jbDelete = new BaseJB("삭제");
		jbClose = new BaseJB("닫기");
	}

	@Override
	public void design() {
		jpTop.addChild();
		jpTop.jpLeft.add(jlName);
		jpTop.jpRight.add(jlWritingDate);

		jpCenter.add(jtfContents);

		jpBottom.addChild();
		jpBottom.jpRight.setFlow();
		jpBottom.jpRight.add(jbUpdate);
		jpBottom.jpRight.add(jbDelete);
		jpBottom.jpRight.add(jbClose);

	}

	@Override
	public void event() {
		jbClose.addActionListener(e -> {
			super.dispose();
		});
	}

}
