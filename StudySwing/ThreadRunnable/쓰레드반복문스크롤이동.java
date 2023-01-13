import java.awt.Image;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowStateListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import bases.BaseBT;
import bases.BaseFrame;
import bases.BaseJL;
import bases.BaseJP;

public class 쓰레드반복문스크롤이동 extends BaseFrame {

	public static void main(String[] args) {
		new 쓰레드반복문스크롤이동();
	}

	private BaseJP jpImg;
	private JScrollPane jscImg;
	private BaseBT jbStop;
	private BaseBT jbStart;
	private Runnable rScrollStart;
	private Runnable rNumUp;
	private Thread tScrollStart;
	private Runnable rNumDown;
	private Thread tNumUp;
	private Thread tNumDown;
	private Runnable rScrollStop;
	private int scrollvalue = 0;
	private boolean rScrollStartBoolen = false;

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

		jbStop = new BaseBT("멈춤");
		jbStart = new BaseBT("실행");

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

		jbStart.addActionListener(e -> {
			rScrollStartBoolen = true;
			runable();
		});

		jbStop.addActionListener(e -> {
			rScrollStartBoolen = false;
		});

		super.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				rScrollStartBoolen = false;
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void runable() {

		rScrollStart = new Runnable() {

			@Override
			public void run() {

				System.out.println("rScrollUp Run 실행");

				while (rScrollStartBoolen) {

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

		tScrollStart = new Thread(rScrollStart);
		tNumUp = new Thread(rNumUp);
		tNumDown = new Thread(rNumDown);

		tScrollStart.start();
	}

}
