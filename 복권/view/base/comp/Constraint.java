
package base.comp;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class Constraint extends GridBagConstraints {

	public Constraint() {
		this(3);
	}

	public Constraint(int inset) {
		this.insets = new Insets(inset, inset, inset, inset);
	}

	public Constraint(int x, int y, int w, int h, double wx, double wy, int inset) {

		this(inset);
		
		this.gridx = x;
		this.gridy = y;
		this.gridwidth = w;
		this.gridheight = h;
		
		this.weightx = wx;
		this.weighty = wy;
		
		this.fill = GridBagConstraints.BOTH;
		this.anchor = GridBagConstraints.LINE_START;
		
	}

}
