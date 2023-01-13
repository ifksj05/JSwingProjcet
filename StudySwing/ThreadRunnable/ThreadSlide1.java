import javax.swing.JScrollPane;
import javax.swing.JTextField;

import bases.BaseBT;
import bases.BaseFrame;
import bases.BaseJL;
import bases.BaseJP;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ThreadSlide1 extends BaseFrame {

	private BaseJP jpImg;
	private JScrollPane jscImg;
	private BaseBT jbUp;
	private BaseBT jbDown;
	private Runnable rScrollUp;
	private Thread tScrollUp;
	private JTextField jtTest;
	private BaseBT jbStop;
	private BaseBT jbStart;

	private int scrollvalue = 0;

	private boolean isThread = true;

	public static void main(String[] args) {
		new ThreadSlide1();
	}

	public ThreadSlide1() {
		setFrame("Thread 슬라이드 애니메이션 구현", 500, 404);
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
		jtTest = new JTextField();

		jbStop = new BaseBT("멈춰!");
		jbStart = new BaseBT("실행!");

	}

	@Override
	public void design() {
		jpCenter.add(jscImg);

		jpBottom.setGrid(1, 5, 0, 0);
		jpBottom.add(jbUp);
		jpBottom.add(jbDown);
		jpBottom.add(jtTest);
		jpBottom.add(jbStop);
		jpBottom.add(jbStart);

	}

	@Override
	public void event() {

		threadStart();

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

		jbStop.addActionListener(e -> {
			isThread = false;
			System.out.println("Trhead 종료");
		});

		jbStart.addActionListener(e -> {
//			if(isThread) return;
			isThread = true;
//			threadStart();
		});

		jtTest.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				System.out.println("keyPressed " + jtTest.getText());
			}
		});

	}

	private void threadStart() {

		rScrollUp = new Runnable() {

			@Override
			public void run() {

				System.out.println("rScrollUp Run 실행");

				while (isThread) {

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

		tScrollUp = new Thread(rScrollUp);
		tScrollUp.start();

	}

}
