package window.app;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import base.comp.BaseFrame;
import base.comp.Constraint;
import base.comp.ImageLabel;
import base.comp.LocalPanel;
import message.MessageManager;
import res.ResManager;
import window.admin.LoginFrame;
import window.course.ManageCourseFrame;
import window.member.AddMemberFrame;
import window.member.ManageMemberFrame;
import window.reserve.ManageReserveFrame;

public class MainFrame extends BaseFrame {

	private JPanel jpNorth;
	
		private JButton jbAddMember, jbManageMember, jbManageCourse, jbAddReserve, jbClose;
		
	private ImageLabel jlCenter;
	
	public MainFrame() {
		super(600, 500, "학원 관리");
	}

	@Override
	public void setComp() {
		
		jpNorth = new LocalPanel(36, new GridBagLayout());
		
			jbAddMember = new JButton("회원 등록");
			jbManageMember = new JButton("회원 관리");
			jbManageCourse = new JButton("강좌 관리");
			jbAddReserve = new JButton("수강 신청 및 관리");
			jbClose = new JButton("종   료");
		
			JComponent[] jcs = new JComponent[] {jbAddMember, jbManageMember, jbManageCourse, jbAddReserve, jbClose};
			
		for (int i = 0; i < jcs.length; i++) {
			jpNorth.add(jcs[i], new Constraint());
		}
			
		jlCenter = new ImageLabel(7);
		
		try {
			jlCenter.setImage(ImageIO.read(new File(ResManager.RES_PATH + "main.jpg")));
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}

	@Override
	public void setDesign() {
		add(jpNorth, BorderLayout.NORTH);
		add(jlCenter, BorderLayout.CENTER);
	}

	@Override
	public void setAction() {
		
		jbAddMember.addActionListener(e -> {
			dispose();
			new AddMemberFrame().setVisible(true);
		});
		
		jbManageMember.addActionListener(e -> {
			dispose();
			new ManageMemberFrame().setVisible(true);
		});
		
		jbManageCourse.addActionListener(e -> {
			dispose();
			new ManageCourseFrame().setVisible(true);
		});
		
		jbAddReserve.addActionListener(e -> {
			dispose();
			new ManageReserveFrame().setVisible(true);
		});
		
		jbClose.addActionListener(e -> {close();});
		
	}

	@Override
	public void close() {
		dispose();
		new LoginFrame().setVisible(true);
	}

}
