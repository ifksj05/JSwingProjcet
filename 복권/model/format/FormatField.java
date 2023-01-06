package format;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFormattedTextField;

import message.MessageManager;

public class FormatField extends JFormattedTextField {

	public FormatField(int length) {
		this(new RegexFormatter(length));
	}

	public FormatField(FormatModel...models) {
		this(new RegexFormatter(models));
	}
	
	public FormatField(RegexFormatter format) {
		super(format);
		setAction();
	}

	private void setAction() {

		this.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				validateData();
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				validateData();
			}

		});

	}

	public void validateData() {

		try {

			commitEdit();

		} catch (Exception e) {
			MessageManager.showErr(e.getMessage());
			setText("");
			setValue(null);
		}

	}

}
