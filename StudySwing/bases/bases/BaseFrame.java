package bases;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame implements BaseI {

	public BaseJP jpMain;
	public BaseJP jpCenter;
	public BaseJP jpTop;
	public BaseJP jpLeft;
	public BaseJP jpRight;
	public BaseJP jpBottom;

	public void setFrame(String title, int width, int height) {

		super.setTitle(title);
		super.setSize(width, height);

		jpMain = new BaseJP();
		jpMain.setBorder(10, 10, 10, 10);

		jpTop = new BaseJP();
		jpLeft = new BaseJP();
		jpCenter = new BaseJP();
		jpRight = new BaseJP();
		jpBottom = new BaseJP();

		make();
		design();
		event();

		jpMain.add(jpTop, BorderLayout.NORTH);
		jpMain.add(jpLeft, BorderLayout.WEST);
		jpMain.add(jpCenter, BorderLayout.CENTER);
		jpMain.add(jpRight, BorderLayout.EAST);
		jpMain.add(jpBottom, BorderLayout.SOUTH);

		super.add(jpMain);

		super.setLocationRelativeTo(null);
		super.setVisible(true);

		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void refresh() {
		super.repaint();
		super.validate();
	}

}
