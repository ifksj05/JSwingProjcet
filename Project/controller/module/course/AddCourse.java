package module.course;

import java.sql.ResultSet;

import admin.Admin;
import course.Course;
import jdbc.DbManager;
import member.Member;
import message.MessageManager;
import module.admin.Login;

public class AddCourse {
	
	public void addCourse(Course course) throws Exception {
		
		if (Login.getAdmin().getpID() == 2) {
			throw new Exception("추가/수정/편집에 대한 권한이 없습니다.");
		}
		
		if (Math.ceil(course.getPrice()) < 60000 || Math.ceil(course.getPrice()) > 130000) {
			throw new Exception("가격은 6만 에서 13만 사이로 만 단위로 입력하셔야 합니다.");
		}
		
		try (DbManager dbMgr = new DbManager()) {
			
			if (isExistsCourse(dbMgr, course)) {
				throw new Exception("이미 존재하는 강좌명입니다.");
			}
			
			String sql = "insert into course values(?, ?, ?)";
			dbMgr.getUpdateResult(sql, course.getCourseName(), course.getTeachName(), Math.ceil(course.getPrice()));
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public static boolean isExistsCourse(DbManager dbMgr, Course course) throws Exception {
		
		String sql = "select * from course where coursename = ?";
		return dbMgr.getResultSet(sql, course.getCourseName()).next();
		
	}
	
}
