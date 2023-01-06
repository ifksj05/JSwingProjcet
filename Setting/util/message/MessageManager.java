package message;

import javax.swing.JOptionPane;

public class MessageManager {

	public static void showMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void showErr(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.ERROR_MESSAGE);
	}
	
	public static boolean getConfirm(String msg) {
		return JOptionPane.showConfirmDialog(null, msg, "Message", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION;
	}
	
}
