import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.ScrollPane;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class imgLable extends JFrame {

	public static void main(String[] args) {
		new imgLable();
	}

	private int value;

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

		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(1, 320	));
		JButton front = new JButton("맨 앞으로");
		JButton last = new JButton("맨 뒤로");
		JButton plus = new JButton("+10");
		JButton minus = new JButton("-10");
		jp2.add(front);
		jp2.add(last);
		jp2.add(plus);
		jp2.add(minus);

		JScrollPane jsp = new JScrollPane(jp, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		front.addActionListener(e -> {
			value = 0;
			jsp.getHorizontalScrollBar().setValue(value);
		});

		last.addActionListener(e -> {
			value = jsp.getHorizontalScrollBar().getMaximum();
			jsp.getHorizontalScrollBar().setValue(value);
		});

		plus.addActionListener(e -> {
			value += 10;
			jsp.getHorizontalScrollBar().setValue(value);
		});

		minus.addActionListener(e -> {
			value -= 10;
			jsp.getHorizontalScrollBar().setValue(value);
		});

		super.add(jsp, BorderLayout.CENTER);
		super.add(jp2, BorderLayout.SOUTH);

		super.setVisible(true);
		super.setSize(500, 500);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		jsp.getHorizontalScrollBar().setValue(768);

		while (true) {
			try {

				Thread.sleep(100);
				value += 10;
				jsp.getHorizontalScrollBar().setValue(value);
				System.out.println("현재 값" + jsp.getHorizontalScrollBar().getValue());
				System.out.println("최대 값" + jsp.getHorizontalScrollBar().getMaximum());
				System.out.println("스크롤바 크기" + jsp.getHorizontalScrollBar().getSize());

//				max = 

				if (jsp.getHorizontalScrollBar().getValue() == jsp.getHorizontalScrollBar().getMaximum()
						- jsp.getHorizontalScrollBar().getSize().height) {

					System.out.println("최대");
					value = 0;
					jsp.getHorizontalScrollBar().setValue(value);
				}

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
