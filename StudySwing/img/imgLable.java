import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class imgLable extends JFrame {

	public static void main(String[] args) {
		new imgLable();
	}

	public imgLable() {

		ImageIcon icon = new ImageIcon(
				new ImageIcon("./image/1.jpg").getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
		JLabel jl = new JLabel(icon);
		ImageIcon icon1 = new ImageIcon(
				new ImageIcon("./image/2.jpg").getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
		JLabel jl1 = new JLabel(icon1);
		ImageIcon icon2 = new ImageIcon(
				new ImageIcon("./image/3.jpg").getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT));
		JLabel jl2 = new JLabel(icon2);

		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(1, 3));

		jp.add(jl);
		jp.add(jl1);
		jp.add(jl2);

//		ScrollPane jsp = new ScrollPane();
		JScrollPane jsp = new JScrollPane();
		jsp.add(jp);

		super.add(jsp, BorderLayout.CENTER);

		super.setVisible(true);
		super.setSize(500, 500);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
