import javax.swing.JScrollPane;

import bases.BaseBT;
import bases.BaseFrame;
import bases.BaseJL;
import bases.BaseJP;

public class test1 extends BaseFrame {

	private BaseJP jpImg;
	private JScrollPane jscImg;
	private BaseBT jbUp;
	private BaseBT jbDown;

	public static void main(String[] args) {
		new test1();
	}

	public test1() {
		setFrame("반복 슬라이드 애니메이션 구현", 500, 404);
		next();
	}

	@Override
	public void make() {

		jpImg = new BaseJP();
		jpImg.setGrid(1, 3, 0, 0);

		jpImg.add(new BaseJL().setImg("./image/1.jpg", 500, 300));
		jpImg.add(new BaseJL().setImg("./image/2.jpg", 500, 300));
		jpImg.add(new BaseJL().setImg("./image/3.jpg", 500, 300));

		jscImg = new JScrollPane(jpImg);

		jbUp = new BaseBT("클릭1");
		jbDown = new BaseBT("클릭2");

	}

	@Override
	public void design() {
		jpCenter.add(jscImg);

		jpBottom.setGrid(1, 2, 0, 0);
		jpBottom.add(jbUp);
		jpBottom.add(jbDown);

	}

	@Override
	public void event() {

		jbUp.addActionListener(e -> {

			for (int i = 1; i <= 100; i++) {
				System.out.println("숫자 증가 : " + i);
			}
		});

		jbDown.addActionListener(e -> {
			for (int i = 100; i >= 1; i--) {
				System.out.println("숫자 감소 : " + i);
			}
		});
	}

	private void next() {
		System.out.println("rScrollUp Run 실행");
		int scrollvalue = 0;

		while (true) {

			// if : 스크롤 값이 최대라면 처음으로 이동하라
			if (jscImg.getHorizontalScrollBar().getValue() == jscImg.getHorizontalScrollBar().getMaximum()
					- jscImg.getHorizontalScrollBar().getSize().width) {
				scrollvalue = 0;
			}
			scrollvalue += 10;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jscImg.getHorizontalScrollBar().setValue(scrollvalue);
		}
	}
}
