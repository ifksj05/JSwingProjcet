import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame_sub extends JFrame {

	private MainFrame mainFrame;
	private JPanel center;
	private JPanel top;
	private JButton up;
	private JButton down;
	private JLabel lb;
	private int year;
	private int month;
	private String ym;
	private Calendar cl;
	private int day;
	private JPanel center_top;
	private JPanel center_center;
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	private JLabel jl4;
	private JLabel jl5;
	private JLabel jl6;
	private JLabel jl7;
	private JPanel tmp;
	private Vector<BaseBt> btArr;

	public MainFrame_sub(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		super.setTitle("날짜 선택");
		super.setSize(200, 230);
		////////////////////////////////////

		// 생성

		jl1 = new JLabel("일");
		jl2 = new JLabel("월");
		jl3 = new JLabel("화");
		jl4 = new JLabel("수");
		jl5 = new JLabel("목");
		jl6 = new JLabel("금");
		jl7 = new JLabel("토");

		top = new JPanel();
		top.setLayout(new BorderLayout());
		center = new JPanel();
		center.setLayout(new BorderLayout());

		cl = Calendar.getInstance();

		// 현재 날짜
		year = cl.get(Calendar.YEAR);
		month = cl.get(Calendar.MONTH);
		day = cl.get(Calendar.DAY_OF_MONTH);

		// 현재 날짜 2023 0 8
		setDate(year, month, day);

		System.out.println(month + 1 + "월 시작 위치 : " + firstDay(year, month));
		System.out.println(month + 1 + "월 끝 위치 : " + lastDay(year, month));
		ym = year + " / " + month + 1;

		center_top = new JPanel();
		center_top.setLayout(new GridLayout(1, 7, 3, 3));
		center_center = new JPanel();
		center_center.setLayout(new GridLayout(6, 7, 3, 3));

		down = new JButton("<");
		lb = new JLabel(ym);
		lb.setHorizontalAlignment(JLabel.CENTER);
		up = new JButton(">");

		// 추가
		top.add(down, BorderLayout.WEST);
		top.add(lb, BorderLayout.CENTER);
		top.add(up, BorderLayout.EAST);

		center_top.add(jl1);
		center_top.add(jl2);
		center_top.add(jl3);
		center_top.add(jl4);
		center_top.add(jl5);
		center_top.add(jl6);
		center_top.add(jl7);
		jl1.setHorizontalAlignment(JLabel.CENTER);
		jl2.setHorizontalAlignment(JLabel.CENTER);
		jl3.setHorizontalAlignment(JLabel.CENTER);
		jl4.setHorizontalAlignment(JLabel.CENTER);
		jl5.setHorizontalAlignment(JLabel.CENTER);
		jl6.setHorizontalAlignment(JLabel.CENTER);
		jl7.setHorizontalAlignment(JLabel.CENTER);

		center.add(center_top, BorderLayout.NORTH);
		center.add(center_center, BorderLayout.CENTER);

		super.add(top, BorderLayout.NORTH);
		super.add(center, BorderLayout.CENTER);

		// 이벤트

		tmp = new JPanel();
		for (int i = 0; i < firstDay(year, month); i++) {
			center_center.add(tmp);
		}
		btArr = new Vector<BaseBt>();
		for (int i = 0; i <= lastDay(year, month); i++) {
			btArr.add(new BaseBt(i + 1 + ""));
		}
		for (int i = 0; i <= lastDay(year, month); i++) {
			center_center.add(btArr.get(i));
		}
//		center_center

		////////////////////////////////////
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void setDate(int year, int mon, int day) {
		cl.set(year, mon, day);

		this.year = cl.get(Calendar.YEAR);
		this.month = cl.get(Calendar.MONTH);
		this.day = cl.get(Calendar.DAY_OF_MONTH);
	}

	public int firstDay(int year, int mon) {
		int num;
		cl.set(year, mon, 1);

		num = cl.get(Calendar.DAY_OF_WEEK);

		return num;
	}

	public int lastDay(int year, int mon) {
		int num;
		cl.set(year, mon, 1);

		num = cl.getActualMaximum(Calendar.DAY_OF_MONTH);

		return num;
	}

}
