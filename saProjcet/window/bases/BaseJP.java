package bases;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class BaseJP extends JPanel {

	public BaseJP jpMain;
	public BaseJP jpCenter;
	public BaseJP jpTop;
	public BaseJP jpLeft;
	public BaseJP jpRight;
	public BaseJP jpBottom;

	public BaseJP() {
		super.setLayout(new BorderLayout());
	}

	public BaseJP addChild() {

		jpTop = new BaseJP();
		jpLeft = new BaseJP();
		jpCenter = new BaseJP();
		jpRight = new BaseJP();
		jpBottom = new BaseJP();

		super.add(jpTop, BorderLayout.NORTH);
		super.add(jpLeft, BorderLayout.WEST);
		super.add(jpCenter, BorderLayout.CENTER);
		super.add(jpRight, BorderLayout.EAST);
		super.add(jpBottom, BorderLayout.SOUTH);

		return this;

	}

	public BaseJP setGrid(int rows, int cols, int hgap, int vgap) {

		super.setLayout(new GridLayout(rows, cols, hgap, vgap));

		return this;

	}

	public BaseJP setFlow() {
		super.setLayout(new FlowLayout());

		return this;

	}
}
