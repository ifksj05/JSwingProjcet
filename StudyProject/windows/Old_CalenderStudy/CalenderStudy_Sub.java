package Old_CalenderStudy;

import java.util.Calendar;
import java.util.Vector;

import javax.swing.JPanel;

import bases.BaseBt;
import bases.BaseFrame;
import bases.BaseLb;

public class CalenderStudy_Sub extends BaseFrame {
	private String date;
	private BaseBt dateBack;
	private BaseBt dateFront;
	private Vector<BaseBt> btArr;
	private int daySize;
	private Vector<Integer> monthArr;
	private String returnDate;
	private CalenderStudy calenderStudy;
	private int year;
	private int month;
	private int day;

	public CalenderStudy_Sub(CalenderStudy calenderStudy) {
		this.calenderStudy = calenderStudy;
		setFrame("날짜 선택", 600, 500);
	}

	@Override
	public void make() {

		Calendar time = Calendar.getInstance(); // 생성 방법
		int year = time.get(Calendar.YEAR);
		int month = time.get(Calendar.MONTH);

		time.set(year, month, 1);

		int firstWeekDay = time.get(Calendar.DAY_OF_WEEK);

		daySize = time.getActualMaximum(Calendar.DAY_OF_MONTH);
//		time.set(2023, 1, 6);

		System.out.println(firstWeekDay);

		for (int i = 0; i < firstWeekDay - 1; i++) {
			center.add(new JPanel());
		}

		monthArr = new Vector<Integer>();
		for (int i = 0; i < 12; i++) {
			monthArr.add(i + 1);
		}

		this.year = time.get(Calendar.YEAR);
		this.month = time.get(Calendar.MONTH + 1);
		this.day = time.get(Calendar.DAY_OF_MONTH);

		date = this.year + " - " + this.month;

		dateBack = new BaseBt("<");
		dateFront = new BaseBt(">");

		btArr = new Vector<BaseBt>();
		for (int i = 0; i < daySize; i++) {
			btArr.add(new BaseBt(i + 1 + ""));
		}

	}

	@Override
	public void disign() {
		top.addChild();
		top.center.add(new BaseLb(date).setCenter().setFont(30));

		top.left.add(dateBack);
		top.right.add(dateFront);

		center.setBorder(25, 15, 25, 15);
		center.setGrid(6, 7, 10, 10);
		for (int i = 0; i < btArr.size(); i++) {
			center.add(btArr.get(i));
		}
	}

	@Override
	public void event() {

		dateFront.addActionListener(e -> {

		});

		for (int i = 0; i < btArr.size(); i++) {
			int ii = i;
			btArr.get(i).addActionListener(e -> {
//				System.out.println("연" + date.split("-")[0]);
//				System.out.println("월" + date.split("-")[1]);
//				System.out.println("일 : " + btArr.get(ii).getText());

				returnDate = date.split("-")[0] + "-" + date.split("-")[1] + "-" + btArr.get(ii).getText();
				System.out.println(returnDate);

				calenderStudy.dateRefresh(returnDate);

				dispose();

			});

		}
	}

	public void setDate(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public String returnDate() {
		return returnDate;

	}

}
