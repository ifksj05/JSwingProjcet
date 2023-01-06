package window.member;

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
import format.FormatField;
import member.Member;
import message.MessageManager;
import module.member.ModifyMember;
import window.app.MainFrame;

public class ModifyMemberFrame extends BaseFrame {

	private JPanel jpCenter;
	
		private JTextField jtCode, jtDate;
		
		private FormatField jtName, jtPhone, jtAddr;
		
	private JPanel jpNorth;
	
		private JButton jbAdd, jbClose;
	
	private Member member = null;
	private String originalName = null;
		
	public ModifyMemberFrame() {
		super(350, 250, "ȸ�� ���� ����");
	}

	@Override
	public void setComp() {
		
		jpCenter = new LocalPanel(new GridLayout(6, 2));
		
			jtCode = new JTextField();
			jtCode.setEditable(false);
		
			jtDate = new JTextField();
			jtDate.setEditable(false);
			
			jtName = new FormatField(30);
			jtPhone = new FormatField(20);
			jtAddr = new FormatField(30);

			String[] cols = new String[] {"ȸ �� �� �� :", "ȸ �� �� :", "�� �� �� :", "��  �� :", "�� �� �� :"};
			JComponent[] jcs = new JComponent[] {jtCode, jtName, jtPhone, jtAddr, jtDate};
			
		for (int i = 0; i < jcs.length; i++) {
			jpCenter.add(new JLabel(cols[i]), new Constraint(0, i, 1, 1, 0, 0, 5));
			jpCenter.add(jcs[i], new Constraint(1, i, 1, 1, 1, 1, 5));
		}
		
		jpNorth = new LocalPanel(36, new GridBagLayout());
		
			jbAdd = new JButton("����");
			jbClose = new JButton("���");
			
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
				
				if (jtName.getText().equals("")) {
					throw new Exception("�̸��� �ݵ�� �Է��ϼ���");
				}
				
				member.setMemberName(jtName.getText());
				member.setPhonee(jtPhone.getText());
				member.setAddress(jtAddr.getText());
				
				new ModifyMember().modifyMember(member);
				MessageManager.showMsg(originalName + " ���� ȸ������������ �Ϸ�Ǿ����ϴ�.", "�޽���");
				close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage(), "ȸ������ ����");
			}
			
		});
		
		jbClose.addActionListener(e -> {close();});
		
	}

	public void setMember(Member member) {
		
		this.member = member;
		this.originalName = member.getMemberName();
		
		JTextField[] jcs = new JTextField[] {jtCode, jtName, jtPhone, jtAddr, jtDate};
		Object[] objs = this.member.getObjectArray();
		
		for (int i = 0; i < objs.length; i++) {
			jcs[i].setText(objs[i] + "");
		}
		
	}
	
	@Override
	public void close() {
		
		dispose();
		new ManageMemberFrame().setVisible(true);
		
	}

}
