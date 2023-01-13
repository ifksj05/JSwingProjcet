import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import bases.BaseFrame;
import bases.BaseJL;
import bases.BaseJP;

public class 쓰레드반복문스크롤이동 extends BaseFrame {

	public static void main(String[] args) {
		new 쓰레드반복문스크롤이동();
	}

	private BaseJP jpImg;
	private JScrollPane jscImg;
	private BaseBt jbStop;
	private BaseBt jbStart;
	private Runnable rScrollUp;
	private Runnable rNumUp;
	private Thread tScrollUp;
	private Runnable rNumDown;
	private Thread tNumUp;
	private Thread tNumDown;

	public 쓰레드반복문스크롤이동() {
		setFrame("쓰레드, 스크롤 공부", 500, 404);
	}

	@Override
	public void make() {

		jpImg = new BaseJP();
		jpImg.setGrid(1, 3, 0, 0);

		jpImg.add(new BaseJL().setImg("./image/1.jpg", 500, 300));
		jpImg.add(new BaseJL().setImg("./image/2.jpg", 500, 300));
		jpImg.add(new BaseJL().setImg("./image/3.jpg", 500, 300));

		jscImg = new JScrollPane(jpImg);

		jbStop = new BaseBt("멈춤");
		jbStart = new BaseBt("실행");

	}

	@Override
	public void design() {
		jpCenter.add(jscImg);

		jpBottom.setGrid(1, 2, 0, 0);
		jpBottom.add(jbStop);
		jpBottom.add(jbStart);

	}

	@Override
	public void event() {

		runable();
		tScrollUp.start();

		jbStart.addActionListener(e -> {
//			tScrollUp.start();
			tNumUp.start();
		});

		jbStop.addActionListener(e -> {
			tNumDown.start();

		});

	}

	private void runable() {

		rScrollUp = new Runnable() {

			private int scrollvalue = 0;

			@Override
			public void run() {

				System.out.println("rScrollUp Run 실행");

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
		};

		rNumUp = new Runnable() {

			@Override
			public void run() {

				for (int i = 1; i <= 100; i++) {
					System.out.println("숫자 증가 : " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};

		rNumDown = new Runnable() {

			@Override
			public void run() {

				for (int i = 100; i >= 1; i--) {
					System.out.println("숫자 감소 : " + i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		};

		tScrollUp = new Thread(rScrollUp);
		tNumUp = new Thread(rNumUp);
		tNumDown = new Thread(rNumDown);

	}

}
