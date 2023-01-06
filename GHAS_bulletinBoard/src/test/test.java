package test;

import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFileChooser;

import bases.BaseFrame;

public class test extends BaseFrame {

	public static void main(String[] args) {
		new test();
	}

	private JButton button;

	public test() {
		setFrame("test", 500, 500);

	}

	@Override
	public void make() {
		button = new JButton();
		button.setEnabled(false);

	}

	@Override
	public void disign() {
		center.add(button);

	}

	@Override
	public void event() {

	}

}
