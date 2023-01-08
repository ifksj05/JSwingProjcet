import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	public static void main(String[] args) {
		new MainFrame();
	}

	private JTextField dateTf;
	private JLabel lb;
	private MainFrame mainFrame;

	public MainFrame() {

		super.setTitle("캘린더");
		super.setSize(500, 500);
		super.setLayout(new FlowLayout());

		///////////////////////////////////////

		// 생성
		lb = new JLabel("날짜 선택");
		dateTf = new JTextField(10);
		dateTf.setText("yyyy-mm-dd");
		dateTf.setEnabled(false);

		// 추가
		super.add(lb);
		super.add(dateTf);

		// 이벤트
		mainFrame = this;
		dateTf.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new MainFrame_sub(mainFrame);
			}

		});

		///////////////////////////////////////

		super.setVisible(true);
		super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void setDateTf(String txt) {
		this.dateTf.setText(txt);
	}

}
