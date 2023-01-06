package window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import base.comp.BaseFrame;
import base.comp.LocalPanel;

public class View extends BaseFrame {

	private JLabel jlTitle;
	
	private JPanel jpCenter;
	
	public View() {
		super(400, 200, "GIFS 복권");
	}
	
	@Override
	public void setComp() {
		
		jlTitle = new JLabel("GIFS 복권", JLabel.CENTER);
		jlTitle.setPreferredSize(new Dimension(0, 40));
		
		getContentPane().setBackground(Color.lightGray);
	
		jpCenter = new LocalPanel();
		
		Vector<Integer> numAry = new Vector();
		
		String randStr = "";
		
		for (int i = 1; i <= 10; i++) {
			numAry.add(i);
		}
		
		for (int i = 1; i <= 6; i++) {
			
			int randNum = (int) (System.nanoTime() % numAry.size());
			randStr += numAry.get(randNum) + " ";
			numAry.remove(randNum);
			
		}
		
		jpCenter.add(new JLabel(randStr, JLabel.CENTER));
		
	}

	@Override
	public void setDesign() {
		add(jlTitle, BorderLayout.NORTH);
		add(jpCenter, BorderLayout.CENTER);
	}

	@Override
	public void setAction() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	
	
}
