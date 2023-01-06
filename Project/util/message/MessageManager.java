package message;

import javax.swing.JOptionPane;

public class MessageManager {

	public static void showMsg(String msg, String title) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showErr(String msg, String title) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
	}
	
	
	public static void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showErr(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.ERROR_MESSAGE);
	}
	
	public static boolean getConfirm(String msg, String title) {
		return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
	}
	
}
