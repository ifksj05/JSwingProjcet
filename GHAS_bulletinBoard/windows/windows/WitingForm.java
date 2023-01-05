package windows;

import javax.swing.JTextField;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseLb;
import res.ResManager;

public class WitingForm extends BaseFrame {

	public static void main(String[] args) {
		new WitingForm();
	}

	private JTextField titleTf;
	private JTextField contentsTf;
	private BaseBt saveBt;

	public WitingForm() {
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

	}

}
