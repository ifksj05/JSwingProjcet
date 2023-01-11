package bases;

import java.awt.Font;

import javax.swing.JLabel;

public class BaseJL extends JLabel {
	public BaseJL() {

	}

	public BaseJL(String text) {
		super(text);
	}

	public BaseJL setFont(int size) {
		super.setFont(new Font("", Font.BOLD, size));
		;
		return this;
	}

}
