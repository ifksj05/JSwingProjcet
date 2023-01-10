package model;

import javax.swing.JOptionPane;

public class msg {

	public static void info(String massgage) {
		JOptionPane.showMessageDialog(null, massgage, "알림", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void error(String massgage) {
		JOptionPane.showMessageDialog(null, massgage, "에러", JOptionPane.ERROR_MESSAGE);
	}
}
