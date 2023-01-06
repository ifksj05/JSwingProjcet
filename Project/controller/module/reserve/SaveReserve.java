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
			
			bufWtr.write("출력일 : " + new SimpleDateFormat("yyyy년  MM월 dd일 E요일").format(Calendar.getInstance().getTime()) + "\t\t\t강좌명 : " + course.getCourseName());
			bufWtr.newLine();
			bufWtr.newLine();
			
			bufWtr.write("등록코드\t회원코드\t회원명\t강좌명\t강좌수강료\t수강기간\t수강료\t수강신청일");
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
			chooser.setSelectedFile(new File(course.getCourseName() + "수강현황"));
			
			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();
			} else {
				throw new Exception("취소하셨습니다.");
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
