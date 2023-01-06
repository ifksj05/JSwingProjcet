package window.reserve;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import base.comp.BaseFrame;
import base.comp.LocalPanel;
import coursemanage.sub.CourseManageSub;
import coursemanage.sub.CourseManageSubFactory;
import message.MessageManager;
import provider.PubProivider;

public class ChartFrame extends BaseFrame {

	private JPanel jpNorth;
	
		private JButton jbClose;
		
	private JPanel jpEast;
	
		private JScrollPane jspList;
		
			private JPanel jpList;
	
	private Vector<Color> colorList = null;
			
	public ChartFrame() {
		super(400, 320, "¼ö°­ÇöÈ²Â÷Æ®");
	}

	@Override
	public void setComp() {
		
		jpNorth = new LocalPanel(40, new FlowLayout(FlowLayout.RIGHT));
			
			jbClose = new JButton("´Ý±â");
		
		jpNorth.add(jbClose);
			
		jpEast = new LocalPanel(100, 0, new BorderLayout());
		jpEast.setBorder(new EmptyBorder(20, 0, 0, 0));	
		
			jspList = new JScrollPane();
			jspList.setBorder(new EmptyBorder(0, 0, 0, 0));
			
				jpList = new JPanel();
				jpList.setLayout(new BoxLayout(jpList, BoxLayout.Y_AXIS));
				
			jspList.getViewport().setView(jpList);
		
		jpEast.add(jspList);
		
	}

	@Override
	public void setDesign() {
		add(jpNorth, BorderLayout.NORTH);
		add(jpEast, BorderLayout.EAST);
	}

	@Override
	public void setAction() {
		jbClose.addActionListener(e -> {close();});
	}

	@Override
	public void close() {
		dispose();
		new ManageReserveFrame().setVisible(true);
	}

	public void paint(Graphics g) {
		
		super.paint(g);
		
		try {
			
			if (colorList == null) {
				colorList = new Vector<>();
			}
			
			String sql = "select *, count(coursename) as cnt  from coursemanage group by coursename";
			List<CourseManageSub> cmsList = PubProivider.getModelList(new CourseManageSubFactory(), sql);
			
			double sum = cmsList.stream().mapToInt(CourseManageSub :: getCnt).sum() * 1D;
			double lastArc = -90, curArc = 0;
			
			int cnt = 0;
			for (int i = 0; i < cmsList.size(); i++) {
			
				double percent = cmsList.get(i).getCnt() / sum;
				
				
				while (true) {
					
					Color color = new Color(new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
					
					if (!colorList.contains(color) || cnt <= colorList.size() - 1) {
						
						if (cnt > colorList.size() - 1) {
							colorList.add(color);
						}
						
						color = colorList.get(cnt++);
						curArc = 360 * percent;
						
						g.setColor(color);
						g.fillArc(30, 50, 250, 250, (int) lastArc + 1, (int) curArc + 1);
						
						lastArc += curArc;
						
						ItemLabel il = new ItemLabel(color, cmsList.get(i).getCourseName());
						jpList.add(il);
						
						break;
						
					}
					
				}
				
				jpList.paintAll(jpList.getGraphics());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}
	
	public class ItemLabel extends JLabel {
		
		private Color color;
		
		public ItemLabel(Color color, String text) {
			
			this.color = color;
			
			setText("      " + text);
			setMaximumSize(new Dimension(Integer.MAX_VALUE, 20));
			
			setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 12));
			
		}
		
		public void paint(Graphics g) {
			
			super.paint(g);
			g.setColor(color);
			g.fillRect(5, 7, 10, 10);
			
		}
		
	}
	
}
