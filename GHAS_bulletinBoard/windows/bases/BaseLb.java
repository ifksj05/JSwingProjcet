package bases;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;

public class BaseLb extends JLabel {
	public BaseLb() {
	}

	public BaseLb(String txt) {
		super(txt);
	}

	public BaseLb setCenter() {
		super.setHorizontalAlignment(JLabel.CENTER);
		return this;
	}

	public BaseLb setFont(int size) {
		super.setFont(new Font("", Font.BOLD, size));
		return this;
	}
}
