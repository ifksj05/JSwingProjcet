package admin;

import java.sql.ResultSet;

import base.IFactory;

public class AdminFactory implements IFactory<Admin> {

	@Override
	public void bindModel(Admin model, ResultSet rs) {
		
		try {
			
			model.setAllowLoginDate(rs.getTimestamp("allowLoginDate").toLocalDateTime());
			model.setId(rs.getString("id"));
			model.setFailCnt(rs.getInt("failCnt"));
			model.setName(rs.getString("name"));
			model.setPasswd(rs.getString("passwd"));
			model.setpID(rs.getInt("pID"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Admin getModel(ResultSet rs) {
		
		Admin admin = new Admin();
		
		bindModel(admin, rs);
		
		return admin;
		
	}

}
