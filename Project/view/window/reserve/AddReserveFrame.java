package window.reserve;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.comp.BaseFrame;
import base.comp.Constraint;
import base.comp.LocalPanel;
import comp.CourseList;
import comp.MemberList;
import course.Course;
import format.FormatField;
import member.Member;
import message.MessageManager;
import module.member.AddMember;
import module.reserve.AddReserve;
import module.reserve.SaveReserve;
import window.app.MainFrame;

public class AddReserveFrame extends BaseFrame {

	private JLabel jlTitle;
	
	private JPanel jpCenter;
	
		private JTextField jtPrice, jtName;
		private JLabel jlDate;
		
		private FormatField jtPeriod;
		
		private MemberList mList;
		private CourseList cList;
		
	private JPanel jpNorth;
	
		private JButton jbAdd, jbClose;
	
	public AddReserveFrame() {
		
		super(300, 350, "수강신청");
		
		if (mList.getItemCount() > 0) {
			mList.setSelectedIndex(0);
		}
		
		if (cList.getItemCount() > 0) {
			cList.setSelectedIndex(0);
		}
		
	}

	@Override
	public void setComp() {
		
		jlTitle = new JLabel("수 강 신 청", JLabel.CENTER);
		jlTitle.setPreferredSize(new Dimension(0, 40));
		
		jpCenter = new LocalPanel(new GridBagLayout());
		
			jtPrice = new JTextField();
			jtPrice.setEditable(false);
		
			jlDate = new JLabel(new SimpleDateFormat("yyyy년 M월 dd일").format(Calendar.getInstance().getTime()), JLabel.RIGHT);
			
			jtName = new JTextField();
			jtName.setEditable(false);
			
			jtPeriod = new FormatField(20);

			mList = new MemberList();
			cList = new CourseList();
			
			String[] cols = new String[] {"회 원 코 드 :", "회 원 명 :", "강 좌 명 :", "과 목 수 강 료 :", "수 강 기 간 :", "신 청 일 :"};
			JComponent[] jcs = new JComponent[] {mList, jtName, cList, jtPrice, jtPeriod, jlDate};
			
		for (int i = 0; i < jcs.length; i++) {
			jpCenter.add(new JLabel(cols[i], JLabel.RIGHT), new Constraint(0, i, 1, 1, 0, 0, 5));
			jpCenter.add(jcs[i], new Constraint(1, i, 1, 1, 1, 1, 5));
		}
		
		jpNorth = new LocalPanel(36, new GridBagLayout());
		
			jbAdd = new JButton("추가");
			jbClose = new JButton("닫기");
			
		jpNorth.add(jbAdd, new Constraint());
		jpNorth.add(jbClose, new Constraint());
		
	}

	@Override
	public void setDesign() {
		add(jlTitle, BorderLayout.NORTH);
		add(jpCenter, BorderLayout.CENTER);
		add(jpNorth, BorderLayout.SOUTH);
	}

	@Override
	public void setAction() {
		
		mList.addActionListener(e -> {
			
			Member member = (Member) mList.getSelectedItem();
			jtName.setText(member.getMemberName());
			
		});
		
		cList.addActionListener(e -> {
			
			Course member = (Course) cList.getSelectedItem();
			jtPrice.setText(member.getPrice() + "");
			
		});
		
		jbAdd.addActionListener(e -> {
			
			try {
				
				if (jtPeriod.getText().equals("")) {
					throw new Exception("수강기간을 선택해주십시오.");
				}
				
				Course course = (Course) cList.getSelectedItem();
				Member member = (Member) mList.getSelectedItem();
				
				new AddReserve().addReserve(course, member, Integer.parseInt(jtPeriod.getText()));
				
				MessageManager.showMsg("수강 신청이 완료되었습니다.");
				close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage(), "수강신청 에러");
			}
			
		});
		
		jbClose.addActionListener(e -> {close();});
		
	}

	@Override
	public void close() {
		
		dispose();
		new ManageReserveFrame().setVisible(true);
		
	}

}
