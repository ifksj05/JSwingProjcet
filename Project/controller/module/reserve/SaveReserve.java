package module.reserve;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import course.Course;
import coursemanage.CourseManage;
import message.MessageManager;

public class SaveReserve {

	public void saveReserve(List<CourseManage> cmList, Course course) throws Exception {
		
		File file = getFile(course);
		String path = file.getPath() + (file.getPath().endsWith(".txt") ? "" : ".txt");
		
		try (BufferedWriter bufWtr = new BufferedWriter(new FileWriter(new File(path)))) {
			
			bufWtr.write("����� : " + new SimpleDateFormat("yyyy��  MM�� dd�� E����").format(Calendar.getInstance().getTime()) + "\t\t\t���¸� : " + course.getCourseName());
			bufWtr.newLine();
			bufWtr.newLine();
			
			bufWtr.write("����ڵ�\tȸ���ڵ�\tȸ����\t���¸�\t���¼�����\t�����Ⱓ\t������\t������û��");
			bufWtr.newLine();
			
			
			for (CourseManage courseManage : cmList) {
				
				Object[] objs = courseManage.getObjectArray();
				String line = "";
				
				for (int i = 0; i < objs.length; i++) {
					line += objs[i] + "\t";
				}
				
				bufWtr.write(line);
				bufWtr.newLine();
				
			}
			
			bufWtr.flush();
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}
	
	public File getFile(Course course) throws Exception {
		
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
			JFileChooser chooser = new JFileChooser();
			chooser.setSelectedFile(new File(course.getCourseName() + "������Ȳ"));
			
			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();
			} else {
				throw new Exception("����ϼ̽��ϴ�.");
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			
			try {
				UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
			} catch (Exception e2) {
				e2.printStackTrace();
				MessageManager.showErr(e2.getMessage());
			}
			
		}
		
	}
	
}
