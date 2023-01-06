package Old_CalenderStudy;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import bases.BaseFrame;

public class CalenderStudy extends BaseFrame {

	private JTextField dateTf;
	private CalenderStudy calenderStudy;

	public CalenderStudy() {
		setFrame("날짜 선택", 400, 500);
	}

	@Override
	public void make() {
		dateTf = new JTextField("yyyy-mm-dd");
		dateTf.setHorizontalAlignment(JTextField.CENTER);

		dateTf.setEditable(false);
	}

	@Override
	public void disign() {
		center.setBorder(200, 20, 200, 20);
		center.add(dateTf);
	}

	@Override
	public void event() {

		calenderStudy = this;
		dateTf.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new CalenderStudy_Sub(calenderStudy);
			}

		});

//		dateTf.addActionListener(e -> {
//			new CalenderStudy_Sub();
//		});
	}

	public void dateRefresh(String returnDate) {
		dateTf.setText(returnDate);
	}

}
