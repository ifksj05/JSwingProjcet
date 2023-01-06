package provider.memberr;

import java.sql.ResultSet;

import jdbc.DbManager;
import member.Member;
import member.MemberFactory;

public class MemberProvider {
	
	public static Member getMember(int code) throws Exception {
		
		try (DbManager dbMgr = new DbManager()) {
			
			ResultSet rs = dbMgr.getResultSet("select * from member where membercode = ?", code);
			
			if (rs.next()) {
				return new MemberFactory().getModel(rs);
			} else {
				return null;
			}
			
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
