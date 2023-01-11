import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class mouse extends JFrame {

	public static void main(String[] args) {
		new mouse();
	}

	private JPopupMenu jpm;
	private JMenuItem item;
	private JMenuItem item2;

	public mouse() {

		jpm = new JPopupMenu();

		item = new JMenuItem("메뉴1");
		item2 = new JMenuItem("메뉴2");

		jpm.add(item);
		jpm.addSeparator();
		jpm.add(item2);

		item.addActionListener(e -> {
			System.out.println("메뉴 클릭");
		});

		super.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getButton());

//				if (e.getButton() == 1) {
//					System.out.println("좌클릭 됨.");
//				}
//				if (e.getButton() == 2) {
//					System.out.println("휠클릭 됨.");
//				}
				if (e.getButton() == 3) {
					System.out.println("우클릭 됨.");

					System.out.println(e.getX() + ", " + e.getY());
					jpm.show(mouse.this, e.getX(), e.getY());

				}

			}

		});

		super.setVisible(true);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setSize(500, 500);
	}

}
