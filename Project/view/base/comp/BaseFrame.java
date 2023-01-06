package base.comp;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import base.IDesign;
import base.IView;

public abstract class BaseFrame extends JFrame implements IDesign, IView {

	public BaseFrame(int w, int h, String title) {
		
		setTitle(title);
		
		setSize(w, h);
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setLayout(new BorderLayout());
		
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent arg0) {
				close();
			}
			
		});
		
		setComp();
		setDesign();
		setAction();
		
	}

}
