package windows;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;

public class SelectContentForm extends BaseFrame {
	private BaseJL jlName;
	private BaseJL jlWritingDate;
	private BaseJL jlContents;
	private BaseJB jbClose;

	public SelectContentForm() {
		setFrame("보기 폼", 500, 500);
	}

	@Override
	public void make() {
		jlName = new BaseJL("작성자 : name");
		jlWritingDate = new BaseJL("작성 날짜 : yyyy-mm-dd");
		jlContents = new BaseJL("contents");

		jbClose = new BaseJB("닫기");

	}

	@Override
	public void design() {
		jpTop.addChild();
		jpTop.jpLeft.add(jlName);
		jpTop.jpRight.add(jlWritingDate);

		jpCenter.add(jlContents);

		jpBottom.addChild();
		jpBottom.jpRight.add(jbClose);

	}

	@Override
	public void event() {
		jbClose.addActionListener(e -> {
			super.dispose();
		});
	}

}
