package base.comp;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;

public class ImageLabel extends JLabel {
	
	private int inset;
	private Image image;
	
	public ImageLabel(int inset) {
		
		this.inset = inset;
		
	}

	public void setImage(Image image) {
		this.image = image;
		repaint();
		
	}
	
	public void paint(Graphics g) {
		
		if (image != null) {
			
			int addNum = inset * 2;
			int width = (int) (getBounds().getWidth() - addNum);
			int height = (int) (getBounds().getHeight() - addNum);
			
			g.drawImage(image, inset, inset, width, height, this);
			
		}
		
		super.paint(g);
		
	}
	
}
