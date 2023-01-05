package bases;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame implements BaseI {
	public BasePn center;
	public BasePn top;
	public BasePn left;
	public BasePn right;
	public BasePn bottom;
	public BasePn main;

	public void setFrame(String title, int width, int height) {
		super.setTitle(title);
		super.setSize(width, height);

		top = new BasePn();
		left = new BasePn();
		center = new BasePn();
		right = new BasePn();
		bottom = new BasePn();

		main = new BasePn();
		main.setBorder(10, 10, 10, 10);
		
		make();
		disign();
		event();

		main.add(top, BorderLayout.NORTH);
		main.add(left, BorderLayout.WEST);
		main.add(center, BorderLayout.CENTER);
		main.add(right, BorderLayout.EAST);
		main.add(bottom, BorderLayout.SOUTH);

		super.add(main);

		super.setLocationRelativeTo(null);
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void repaint() {
		super.repaint();
		super.validate();
	}

}
