package Calender;

import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import bases.BaseFrame;
import bases.BaseLb;

public class MainForm extends BaseFrame {

	private TextField chosenDate;

	public MainForm() {
		setFrame("날짜", 300, 300);
	}

	@Override
	public void make() {
		chosenDate = new TextField("yyyy-mm-dd");
		chosenDate.setEnabled(false);
	}

	@Override
	public void disign() {

		main.setBorder(100, 0, 100, 0);

		top.add(new BaseLb("선택된 날짜"));
		center.add(chosenDate);

	}

	@Override
	public void event() {
		MainForm MainForm = this;
		
		chosenDate.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new MainForm_Sub(MainForm);
			}

		});

	}

	public void setShosenDate(TextField chosenDate) {
		this.chosenDate = chosenDate;
	}

}
