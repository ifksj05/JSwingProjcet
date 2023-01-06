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
			throw new Exception("추가/수정/편집에 대한 권한이 없습니다.");
		}
		
		int price = ((int) ((course.getPrice()) / 10000)) * 10000;
		
		if (price < 60000 || price > 130000) {
			throw new Exception("가격은 6만 에서 13만 사이로 만 단위로 입력하셔야 합니다.");
		}
		
		try (DbManager dbMgr = new DbManager()) {
			
			if (!course.getCourseName().equals(originalName) && AddCourse.isExistsCourse(dbMgr, course)) {
				throw new Exception("이미 존재하는 강좌명입니다.");
			}
			
			String sql = "update course set coursename = ?, teachname = ?, price = ? where coursename = ?";
			dbMgr.getUpdateResult(sql, course.getCourseName(), course.getTeachName(), price, originalName);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
