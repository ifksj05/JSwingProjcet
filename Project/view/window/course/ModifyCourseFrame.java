package window.course;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.Constraint;
import base.comp.LocalPanel;
import course.Course;
import format.FormatField;
import format.FormatModel;
import member.Member;
import message.MessageManager;
import module.course.ModifyCourse;
import module.member.ModifyMember;
import window.app.MainFrame;

public class ModifyCourseFrame extends BaseFrame {

	private JPanel jpCenter;
	
		private FormatField jtName, jtTeach, jtPrice;
		
	private JPanel jpNorth;
	
		private JButton jbAdd, jbClose;
	
	private Course course = null;
	private String originalName = null;
		
	public ModifyCourseFrame() {
		super(350, 250, "강좌 정보 수정");
	}

	@Override
	public void setComp() {
		
		jpCenter = new LocalPanel(new GridLayout(3, 2));
		
			jtName = new FormatField(30);
			jtTeach = new FormatField(20);
			jtPrice = new FormatField(new FormatModel("^[\\d]+$", "숫자만 입력해 주십시오."), new FormatModel(10));

			String[] cols = new String[] {"강 좌 명 :", "강 사 명 :", "수 강 료 :"};
			JComponent[] jcs = new JComponent[] {jtName, jtTeach, jtPrice};
			
		for (int i = 0; i < jcs.length; i++) {
			jpCenter.add(new JLabel(cols[i]), new Constraint(0, i, 1, 1, 0, 0, 5));
			jpCenter.add(jcs[i], new Constraint(1, i, 1, 1, 1, 1, 5));
		}
		
		jpNorth = new LocalPanel(36, new GridBagLayout());
		
			jbAdd = new JButton("수정");
			jbClose = new JButton("취소");
			
		jpNorth.add(jbAdd, new Constraint());
		jpNorth.add(jbClose, new Constraint());
		
	}

	@Override
	public void setDesign() {
		add(jpCenter, BorderLayout.CENTER);
		add(jpNorth, BorderLayout.SOUTH);
	}

	@Override
	public void setAction() {
		
		jbAdd.addActionListener(e -> {
			
			try {
				
				if (jtName.getText().equals("") || jtPrice.getText().equals("") || jtTeach.getText().equals("")) {
					throw new Exception("모든 항목을 입력해 주십시오.");
				}
				
				course.setCourseName(jtName.getText());
				course.setTeachName(jtTeach.getText());
				course.setPrice(Integer.parseInt(jtPrice.getText()));
				
				new ModifyCourse().modifyCourse(course, originalName);
				
				MessageManager.showMsg(originalName + "강좌정보수정이 완료되었습니다.", "메시지");
				close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage(), "메시지");
			}
			
		});
		
		jbClose.addActionListener(e -> {close();});
		
	}

	public void setCourse(Course course) {
		
		this.course = course;
		this.originalName = course.getCourseName();
		
		jtName.setText(course.getCourseName());
		jtTeach.setText(course.getTeachName());
		jtPrice.setText(course.getPrice() + "");
		
	}
	
	@Override
	public void close() {
		
		dispose();
		new ManageCourseFrame().setVisible(true);
		
	}

}
