import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class 과제 extends JFrame {
	public static void main(String[] args) {
		new 과제();
	}

	private JPopupMenu jpm;
	private JMenuItem iMaxSizing;
	private JMenuItem iHello;
	private JMenuItem iClose;

	public 과제() {

		jpm = new JPopupMenu();

		iMaxSizing = new JMenuItem("전체크기");
		iHello = new JMenuItem("인사하기");
		iClose = new JMenuItem("종료하기");

		jpm.add(iMaxSizing);
		jpm.addSeparator();
		jpm.add(iHello);
		jpm.addSeparator();
		jpm.add(iClose);

		iMaxSizing.addActionListener(e -> {
			super.setExtendedState(JFrame.MAXIMIZED_BOTH);
		});
		iHello.addActionListener(e -> {
			System.out.println("안녕하세요");
		});
		iClose.addActionListener(e -> {
			this.dispose();
		});

		super.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getButton());

				if (e.getButton() == 3) {
					System.out.println("우클릭 됨.");

					System.out.println(e.getX() + ", " + e.getY());
					jpm.show(과제.this, e.getX(), e.getY());

				}

			}

		});

		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(500, 500);
	}

}
