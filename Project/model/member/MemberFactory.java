package member;

import java.sql.ResultSet;

import base.IFactory;

public class MemberFactory implements IFactory<Member> {

	@Override
	public void bindModel(Member model, ResultSet rs) {
		
		try {
			
			model.setMemberCode(rs.getInt("memberCode"));
			model.setMemberName(rs.getString("MemberName"));
			model.setPhonee(rs.getString("phone"));
			model.setAddress(rs.getString("address"));
			model.setRegDate(rs.getTimestamp("regDate").toLocalDateTime());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Member getModel(ResultSet rs) {
		
		Member m = new Member();
		
		bindModel(m, rs);
		
		return m;
		
	}

}
