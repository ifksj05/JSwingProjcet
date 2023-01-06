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
		super(450, 300, "ȸ�� ���");
	}

	@Override
	public void setComp() {
		
		jlTitle = new JLabel("ȸ �� �� ��", JLabel.CENTER);
		jlTitle.setPreferredSize(new Dimension(0, 40));
		
		jpCenter = new LocalPanel(new GridBagLayout());
		
			jtCode = new JTextField();
			jtCode.setEditable(false);
		
			jlDate = new JLabel(new SimpleDateFormat("yyyy�� M�� dd��").format(Calendar.getInstance().getTime()), JLabel.RIGHT);
			
			jtName = new FormatField(30);
			jtPhone = new FormatField(20);
			jtAddr = new FormatField(30);

			String[] cols = new String[] {"ȸ �� �� �� :", "*ȸ �� �� :", "�� �� �� :", "��  �� :", "�� �� �� :"};
			JComponent[] jcs = new JComponent[] {jtCode, jtName, jtPhone, jtAddr, jlDate};
			
		for (int i = 0; i < jcs.length; i++) {
			jpCenter.add(new JLabel(cols[i], JLabel.RIGHT), new Constraint(0, i, 1, 1, 0, 0, 5));
			jpCenter.add(jcs[i], new Constraint(1, i, 1, 1, 1, 1, 5));
		}
		
		jpNorth = new LocalPanel(36, new GridBagLayout());
		
			jbAdd = new JButton("�߰�");
			jbClose = new JButton("�ݱ�");
			
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
					throw new Exception("�ʼ��׸�(*)�� ��� �Է��ϼ���");
				}
				
				Member member = new Member();
				
				member.setMemberName(jtName.getText());
				member.setPhonee(jtPhone.getText());
				member.setAddress(jtAddr.getText());
				
				new AddMember().addMember(member);
				MessageManager.showMsg(member.getMemberName() + "ȸ���� ��ϵǾ����ϴ�.", "�޽���");
				close();
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage(), "ȸ����� ����");
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
