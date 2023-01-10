package windows;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;
import bases.BaseTA;
import bases.BaseTF;

public class InsertContentsForm extends BaseFrame {
	private BaseJL jlName;
	private BaseJL jlWritingDate;
	private BaseTA jtfContents;
	private BaseJB jbSave;
	private BaseJB jbClose;

	public InsertContentsForm() {
		setFrame("글쓰기 창", 500, 500);
	}

	@Override
	public void make() {
		jlName = new BaseJL("작성자 : name");
		jlWritingDate = new BaseJL("작성 날짜 : yyyy-mm-dd");
		jtfContents = new BaseTA();

		jbSave = new BaseJB("저장");
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
		jpBottom.jpRight.add(jbSave);
		jpBottom.jpRight.add(jbClose);

	}

	@Override
	public void event() {
		jbClose.addActionListener(e -> {
			super.dispose();
		});
	}

}
