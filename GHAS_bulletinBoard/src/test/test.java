package test;

import java.util.Calendar;

import javax.swing.JFileChooser;

import bases.BaseFrame;

public class test extends BaseFrame {

	public static void main(String[] args) {
		new test();
	}

	private JFileChooser jf;

	public test() {
		setFrame("test", 500, 500);

//		Calendar time = Calendar.getInstance();
//		
//		System.out.println(time.get(Calendar.YEAR));
//		System.out.println(time.get(Calendar.MONTH + 1));
//		System.out.println(time.get(Calendar.DAY_OF_MONTH));

	}

	@Override
	public void make() {
		jf = new JFileChooser();
		
	}

	@Override
	public void disign() {
		center.add(jf);
	}

	@Override
	public void event() {

	}

}
