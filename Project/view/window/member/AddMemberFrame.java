package window.member;

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
import format.FormatField;
import member.Member;
import message.MessageManager;
import module.member.AddMember;
import window.app.MainFrame;

public class AddMemberFrame extends BaseFrame {

	private JLabel jlTitle;
	
	private JPanel jpCenter;
	
		private JTextField jtCode;
		private JLabel jlDate;
		
		private FormatField jtName, jtPhone, jtAddr;
		
	private JPanel jpNorth;
	
		private JButton jbAdd, jbClose;
	
	public AddMemberFrame() {
		super(450, 300, "회원 등록");
	}

	@Override
	public void setComp() {
		
		jlTitle = new JLabel("회 원 등 록", JLabel.CENTER);
		jlTitle.setPreferredSize(new Dimension(0, 40));
		
		jpCenter = new LocalPanel(new GridBagLayout());
		
			jtCode = new JTextField();
			jtCode.setEditable(false);
		
			jlDate = new JLabel(new SimpleDateFormat("yyyy년 M월 dd일").format(Calendar.getInstance().getTime()), JLabel.RIGHT);
			
			jtName = new FormatField(30);
			jtPhone = new FormatField(20);
			jtAddr = new FormatField(30);

			String[] cols = new String[] {"회 원 코 드 :", "*회 원 명 :", "휴 대 폰 :", "주  소 :", "등 록 일 :"};
			JComponent[] jcs = new JComponent[] {jtCode, jtName, jtPhone, jtAddr, jlDate};
			
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
		
		
		jbAdd.addActionListener(e -> {
			
			try {
				
				if (jtName.getText().equals("")) {
					throw new Exception("필수항목(*)을 모두 입력하세요");
				}
				
				Member member = new Member();
				
				member.setMemberName(jtName.getText());
				member.setPhonee(jtPhone.getText());
				member.setAddress(jtAddr.getText());
				
				new AddMember().addMember(member);
				MessageManager.showMsg(member.getMemberName() + "회원이 등록되었습니다.", "메시지");
				close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage(), "회원등록 에러");
			}
			
		});
		
		jbClose.addActionListener(e -> {close();});
		
	}

	@Override
	public void close() {
		
		dispose();
		new MainFrame().setVisible(true);
		
	}

}
