package module.course;

import java.sql.ResultSet;

import admin.Admin;
import course.Course;
import jdbc.DbManager;
import member.Member;
import message.MessageManager;
import module.admin.Login;

public class DeleteCourse {
	
	public void deleteCourse(Course course) throws Exception {
		
		if (Login.getAdmin().getpID() == 2) {
			throw new Exception("추가/수정/편집에 대한 권한이 없습니다.");
		}
		
		try (DbManager dbMgr = new DbManager()) {
			
			String sql = "delete from course where coursename = ?";
			dbMgr.getUpdateResult(sql, course.getCourseName());
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
