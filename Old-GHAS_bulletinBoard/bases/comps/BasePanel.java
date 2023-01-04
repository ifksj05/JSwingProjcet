package comps;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BasePanel extends JPanel {
	public BasePanel jpTop;
	public BasePanel jpLeft;
	public BasePanel jpCenter;
	public BasePanel jpRight;
	public BasePanel jpBottom;

	public BasePanel() {
		super.setLayout(new BorderLayout());
	}

	public BasePanel setGrid(int rows, int cols, int hgap, int vgap) {
		super.setLayout(new GridLayout(rows, cols, hgap, vgap));
		return this;
	}

	public BasePanel setBorder(int top, int left, int bottom, int right) {
		super.setBorder(new EmptyBorder(top, left, bottom, right));
		return this;
	}

	public BasePanel addChild() {
		jpTop = new BasePanel();
		jpLeft = new BasePanel();
		jpCenter = new BasePanel();
		jpRight = new BasePanel();
		jpBottom = new BasePanel();

		super.add(jpTop, BorderLayout.NORTH);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpRight, BorderLayout.EAST);
		super.add(jpBottom, BorderLayout.SOUTH);

		return this;
	}
}
