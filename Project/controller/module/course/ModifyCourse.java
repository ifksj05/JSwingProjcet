package module.course;

import java.sql.ResultSet;

import admin.Admin;
import course.Course;
import jdbc.DbManager;
import member.Member;
import message.MessageManager;
import module.admin.Login;

public class ModifyCourse {
	
	public void modifyCourse(Course course, String originalName) throws Exception {
		
		if (Login.getAdmin().getpID() == 2) {
			throw new Exception("�߰�/����/������ ���� ������ �����ϴ�.");
		}
		
		int price = ((int) ((course.getPrice()) / 10000)) * 10000;
		
		if (price < 60000 || price > 130000) {
			throw new Exception("������ 6�� ���� 13�� ���̷� �� ������ �Է��ϼž� �մϴ�.");
		}
		
		try (DbManager dbMgr = new DbManager()) {
			
			if (!course.getCourseName().equals(originalName) && AddCourse.isExistsCourse(dbMgr, course)) {
				throw new Exception("�̹� �����ϴ� ���¸��Դϴ�.");
			}
			
			String sql = "update course set coursename = ?, teachname = ?, price = ? where coursename = ?";
			dbMgr.getUpdateResult(sql, course.getCourseName(), course.getTeachName(), price, originalName);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
