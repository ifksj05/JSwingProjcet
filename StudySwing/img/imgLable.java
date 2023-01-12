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
		jp.setLayout(new GridLayout(3, 1));

		jp.add(jl);
		jp.add(jl1);
		jp.add(jl2);

		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(1, 4));
		JButton front = new JButton("맨 앞으로");
		JButton last = new JButton("맨 뒤로");
		JButton plus = new JButton("+10");
		JButton minus = new JButton("-10");
		jp2.add(front);
		jp2.add(last);
		jp2.add(plus);
		jp2.add(minus);

		JScrollPane jsp = new JScrollPane(jp, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		front.addActionListener(e -> {
			value = 0;
			jsp.getVerticalScrollBar().setValue(value);
		});

		last.addActionListener(e -> {
			value = jsp.getVerticalScrollBar().getMaximum();
			jsp.getVerticalScrollBar().setValue(value);
		});

		plus.addActionListener(e -> {
			value += 10;
			jsp.getVerticalScrollBar().setValue(value);
		});

		minus.addActionListener(e -> {
			value -= 10;
			jsp.getVerticalScrollBar().setValue(value);
		});

		super.add(jsp, BorderLayout.CENTER);
		super.add(jp2, BorderLayout.SOUTH);

		super.setVisible(true);
		super.setSize(500, 500);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		jsp.getVerticalScrollBar().setValue(768);

		while (true) {
			try {

				Thread.sleep(100);
				value += 10;
				jsp.getVerticalScrollBar().setValue(value);
				System.out.println("현재 값" + jsp.getVerticalScrollBar().getValue());
				System.out.println("최대 값" + jsp.getVerticalScrollBar().getMaximum());
				System.out.println("스크롤바 크기" + jsp.getVerticalScrollBar().getSize());

//				max = 

				if (jsp.getVerticalScrollBar().getValue() == jsp.getVerticalScrollBar().getMaximum() - jsp.getVerticalScrollBar().getSize().height) {

					System.out.println("최대");
					value = 0;
					jsp.getVerticalScrollBar().setValue(value);
				}

			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
