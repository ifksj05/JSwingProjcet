package module.member;

import java.sql.ResultSet;

import admin.Admin;
import jdbc.DbManager;
import member.Member;
import message.MessageManager;
import module.admin.Login;

public class ModifyMember {
	
	public void modifyMember(Member member) throws Exception {
		
		if (Login.getAdmin().getpID() == 2) {
			throw new Exception("추가/수정/편집에 대한 권한이 없습니다.");
		}
		
		try (DbManager dbMgr = new DbManager()) {
			
			String sql = "update member set membername = ?, phone = ?, address = ? where memberCode = ?";
			dbMgr.getUpdateResult(sql, member.getMemberName(), member.getPhonee(), member.getAddress(), member.getMemberCode());
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
