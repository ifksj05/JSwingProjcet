package frame;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import comps.BasePanel;
import i.BaseI;

public abstract class BaseFrame extends JFrame implements BaseI {
	public BasePanel jpMain;
	public BasePanel jpTop;
	public BasePanel jpLeft;
	public BasePanel jpCenter;
	public BasePanel jpRight;
	public BasePanel jpBottom;

	public void setFrame(String title, int width, int height) {

		super.setTitle(title);
		super.setSize(width, height);

		jpTop = new BasePanel();
		jpLeft = new BasePanel();
		jpCenter = new BasePanel();
		jpRight = new BasePanel();
		jpBottom = new BasePanel();

		jpMain = new BasePanel();

		mkComp();
		disign();
		event();

		jpMain.add(jpTop, BorderLayout.NORTH);
		jpMain.add(jpLeft, BorderLayout.WEST);
		jpMain.add(jpCenter, BorderLayout.CENTER);
		jpMain.add(jpRight, BorderLayout.EAST);
		jpMain.add(jpBottom, BorderLayout.SOUTH);

		super.add(jpMain);

		super.setVisible(true);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public void close() {

		super.dispose();

	}

	public void disign() {
		// TODO Auto-generated method stub
		
	}

}
