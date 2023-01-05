package test;

import java.util.Calendar;

import bases.BaseFrame;

public class test extends BaseFrame {

	public static void main(String[] args) {
		new test();
	}

	public test() {
//		setFrame("test", 500, 500);

		Calendar time = Calendar.getInstance();
		
		System.out.println(time.get(Calendar.YEAR));
		System.out.println(time.get(Calendar.MONTH + 1));
		System.out.println(time.get(Calendar.DAY_OF_MONTH));

	}

	@Override
	public void make() {
	}

	@Override
	public void disign() {

	}

	@Override
	public void event() {

	}

}
