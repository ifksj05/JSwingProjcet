package bases;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
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

	public BaseJL setImg(String file, int w, int h) {
		ImageIcon icon = new ImageIcon(new ImageIcon(file).getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT));
		super.setIcon(icon);

		return this;

	}
}
