package windows;

import java.util.Vector;

import bases.BaseFrame;
import bases.BaseJB;
import bases.BaseJL;

public class SelectContentsForm extends BaseFrame {
	private BaseJL jlName;
	private BaseJL jlWritingDate;
	private BaseJL jlContents;
	private BaseJB jbClose;
	private Vector<String> contentsArr;

	public SelectContentsForm(Vector<String> contentsArr) {
		this.contentsArr = contentsArr;
		setFrame(contentsArr.get(2), 500, 350);
	}

	@Override
	public void make() {
		jlName = new BaseJL("작성자 : " + contentsArr.get(1)).setFont(15);
		jlWritingDate = new BaseJL("작성 날짜 : " + contentsArr.get(4)).setFont(15);
		jlContents = new BaseJL(contentsArr.get(3));

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
