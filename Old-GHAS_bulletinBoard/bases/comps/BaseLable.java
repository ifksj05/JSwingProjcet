package comps;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public class BaseLable extends JLabel {
	public BaseLable(String text) {
		super(text);
	}

	public BaseLable() {

	}

	public BaseLable setFont(int size) {

		super.setFont(new Font(null, Font.BOLD, size));
		return this;
	}

	public BaseLable center() {
		super.setHorizontalAlignment(JLabel.CENTER);

		return this;
	}

}
