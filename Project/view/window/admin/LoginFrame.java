package window.admin;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import base.comp.BaseFrame;
import base.comp.ImageLabel;
import base.comp.LocalPanel;
import message.MessageManager;
import module.admin.Login;
import res.ResManager;
import window.app.MainFrame;

public class LoginFrame extends BaseFrame {
	
	private ImageLabel jlCenter;
	
		private JLabel jlTitle;
	
		private JPanel jpCenter;
		
			private JTextField jtId;
			private JPasswordField jpwPw;
			
			private JButton jbLogin, jbClose;
	
	public LoginFrame() {
		super(350, 225, "로그인");
	}

	@Override
	public void setComp() {
		
		jlCenter = new ImageLabel(0);
		jlCenter.setLayout(new BorderLayout());
		
		try {
			
			jlCenter.setImage(ImageIO.read(new File(ResManager.RES_PATH + "login.jpg")));
				
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
			jlTitle = new JLabel("관리자 로그인", JLabel.CENTER);
			jlTitle.setPreferredSize(new Dimension(0, 40));
		
			jpCenter = new LocalPanel(new GridLayout(3, 2, 5, 5));
			jpCenter.setBorder(new EmptyBorder(5, 20, 20, 20));
			
				jtId = new JTextField();
				jpwPw = new JPasswordField();
				
				jbLogin = new JButton("로그인");
				jbClose = new JButton("종료");
				
			jpCenter.add(new JLabel("ID :", JLabel.RIGHT));
			jpCenter.add(jtId);
			jpCenter.add(new JLabel("PASSWORD :", JLabel.RIGHT));
			jpCenter.add(jpwPw);
			jpCenter.add(jbLogin);
			jpCenter.add(jbClose);
				
		jlCenter.add(jlTitle, BorderLayout.NORTH);
		jlCenter.add(jpCenter, BorderLayout.CENTER);
			
	}

	@Override
	public void setDesign() {
		add(jlCenter, BorderLayout.CENTER);
	}

	@Override
	public void setAction() {
		
		jbLogin.addActionListener(e -> {
			
			try {
				
				new Login().login(jtId.getText(), new String(jpwPw.getPassword()));
				dispose();
				
				MessageManager.showMsg("로그인이 완료되었습니다.");
				
				new MainFrame().setVisible(true);
				
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage());
			}
			
		});
		
		jbClose.addActionListener(e -> {close();});
		
	}

	@Override
	public void close() {
		dispose();
		System.exit(0);
	}

}
