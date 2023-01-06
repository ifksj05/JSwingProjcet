package module.reserve;

import java.sql.ResultSet;

import admin.Admin;
import course.Course;
import jdbc.DbManager;
import member.Member;
import message.MessageManager;
import module.admin.Login;

public class AddReserve {
	
	public void addReserve(Course course, Member member, int period) throws Exception {
		
		if (Login.getAdmin().getpID() == 2) {
			throw new Exception("추가/수정/편집에 대한 권한이 없습니다.");
		}
		
		try (DbManager dbMgr = new DbManager()) {
			
			String sql = "insert into coursemanage values(null, ?, ?, ?, ?, ?, ?, now())";
			dbMgr.getUpdateResult(sql, member.getMemberCode(), member.getMemberName(), course.getCourseName(), course.getPrice(), period, getPrice(course, period));
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public static int getPrice(Course course, int period) {
		
		double percent = (period >= 6 ? 0.2 : (period >= 3) ? 0.1 : 0);
		
		return (int) ((1 - percent) * period * course.getPrice());
		
	}
	
}
