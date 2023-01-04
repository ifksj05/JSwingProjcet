package bases;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BasePn extends JPanel {
	public BasePn top;
	public BasePn left;
	public BasePn center;
	public BasePn right;
	public BasePn bottom;

	public BasePn() {
		super.setLayout(new BorderLayout());
	}

	public BasePn setGrid(int rows, int cols, int hgap, int vgap) {
		super.setLayout(new GridLayout(rows, cols, hgap, vgap));
		return this;
	}

	public BasePn addChild() {

		top = new BasePn();
		left = new BasePn();
		center = new BasePn();
		right = new BasePn();
		bottom = new BasePn();

		super.add(top, BorderLayout.NORTH);
		super.add(left, BorderLayout.WEST);
		super.add(center, BorderLayout.CENTER);
		super.add(right, BorderLayout.EAST);
		super.add(bottom, BorderLayout.SOUTH);

		return this;

	}

	public BasePn setBorder(int top, int left, int bottom, int right) {
		super.setBorder(new EmptyBorder(top, left, bottom, right));

		return this;

	}

}
