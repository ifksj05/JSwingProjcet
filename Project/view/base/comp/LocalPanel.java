package base.comp;

import java.awt.BorderLayout;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class LocalPanel extends JPanel {
	
	public LocalPanel() {
		this(0, 0, new BorderLayout());
	}

	public LocalPanel(LayoutManager layout) {
		this(0, layout);
	}

	public LocalPanel(int h, LayoutManager layout) {

		this(0, h, layout);

	}

	public LocalPanel(int w, int h, LayoutManager layout) {

		setSize(w, h);
		setPreferredSize(getSize());

		setOpaque(false);

		setLayout(layout);

	}

}
