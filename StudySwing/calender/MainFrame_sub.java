import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bases.BaseBT;

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
	private Vector<BaseBT> btArr;

	public MainFrame_sub(MainFrame mainFrame) {
		this.mainFrame = mainFrame;

		super.setTitle("날짜 선택");
		super.setSize(500, 500);
		////////////////////////////////////

		// �깮�꽦

		jl1 = new JLabel("일");
		jl2 = new JLabel("월");
		jl3 = new JLabel("화");
		jl4 = new JLabel("수");
		jl5 = new JLabel("목");
		jl6 = new JLabel("금");
		jl7 = new JLabel("일");

		top = new JPanel();
		top.setLayout(new BorderLayout());
		center = new JPanel();
		center.setLayout(new BorderLayout());

		cl = Calendar.getInstance();

		// �쁽�옱 �궇吏�
		year = cl.get(Calendar.YEAR);
		month = cl.get(Calendar.MONTH);
		day = cl.get(Calendar.DAY_OF_MONTH);

		// �쁽�옱 �궇吏� 2023 0 8
		setDate(year, month, day);

		System.out.println(month + 1 + "월 시작 일 : " + getFirstDay(year, month));
		System.out.println(month + 1 + "월 크기 : " + getMonthSize(year, month));
		ym = year + " / " + month + 1;

		center_top = new JPanel();
		center_top.setLayout(new GridLayout(1, 7, 3, 3));
		center_center = new JPanel();
		center_center.setLayout(new GridLayout(6, 7, 3, 3));

		down = new JButton("<");
		lb = new JLabel(ym);
		lb.setHorizontalAlignment(JLabel.CENTER);
		up = new JButton(">");

		// 異붽�
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

		// �씠踰ㅽ듃
		addButton();

		up.addActionListener(e -> {

			month += 1;
			System.out.println(month);
			setDate(year, month, day);
			ym = year + " / " + (month + 1);
			lb.setText(ym);

			center_center.removeAll();
			addButton();
			super.repaint();
			super.validate();

		});
		down.addActionListener(e -> {

			month -= 1;
			setDate(year, month, day);
			ym = year + " / " + (month + 1);
			lb.setText(ym);
			center_center.removeAll();
			addButton();
			super.repaint();
			super.validate();

		});

		////////////////////////////////////
		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void addButton() {
		for (int i = 0; i < getFirstDay(year, month) - 1; i++) {
			center_center.add(new JLabel(""));
		}
		btArr = new Vector<BaseBT>();
		for (int i = 0; i < getMonthSize(year, month); i++) {
			int tmps = i;
			btArr.add(new BaseBT(i + 1 + ""));
			btArr.get(tmps).addActionListener(e -> {
				System.out.println(btArr.get(tmps).getText());

				setDate(year, month, Integer.parseInt(btArr.get(tmps).getText()));
				mainFrame.setDateTf(year + "/" + (month + 1) + "/" + day);

				super.dispose();

			});
		}
		for (int i = 0; i < getMonthSize(year, month); i++) {
			center_center.add(btArr.get(i));
		}
		for (int i = 0; i < 42 - (getMonthSize(year, month) + getFirstDay(year, month)); i++) {
			center_center.add(new JLabel(""));
		}
	}

	public void setDate(int year, int mon, int day) {

		cl.set(year, mon, day);

		this.year = cl.get(Calendar.YEAR);
		this.month = cl.get(Calendar.MONTH);
		this.day = cl.get(Calendar.DAY_OF_MONTH);
	}

	public int getFirstDay(int year, int mon) {
		int num;
		cl.set(year, mon, 1);

		num = cl.get(Calendar.DAY_OF_WEEK);

		return num;
	}

	public int getMonthSize(int year, int mon) {
		int num;
		cl.set(year, mon, 1);

		num = cl.getActualMaximum(Calendar.DAY_OF_MONTH);

		return num;
	}

}
