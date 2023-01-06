package module.reserve;

import java.sql.ResultSet;

import admin.Admin;
import course.Course;
import coursemanage.CourseManage;
import jdbc.DbManager;
import member.Member;
import message.MessageManager;
import module.admin.Login;

public class DeleteReserve {
	
	public void deleteReserve(CourseManage cm) throws Exception {
		
		if (Login.getAdmin().getpID() == 2) {
			throw new Exception("�߰�/����/������ ���� ������ �����ϴ�.");
		}
		
		try (DbManager dbMgr = new DbManager()) {
			
			String sql = "delete from coursemanage where registercode = ?";
			dbMgr.getUpdateResult(sql, cm.getRegisterCode());
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
