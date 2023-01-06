package coursemanage;

import java.sql.ResultSet;

import base.IFactory;

public class CourseManageFactory implements IFactory<CourseManage> {

	@Override
	public void bindModel(CourseManage model, ResultSet rs) {
		
		try {
			
			model.setCourseName(rs.getString("courseName"));
			model.setMemberCode(rs.getInt("memberCode"));
			model.setCrregdate(rs.getDate("crregdate").toLocalDate());		
			model.setCoursePrice(rs.getInt("coursePrice"));
			model.setMemberName(rs.getString("memberName"));
			model.setPeriod(rs.getInt("period"));
			model.setRegisterCode(rs.getInt("registerCode"));
			model.setRegPrice(rs.getInt("regPrice"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public CourseManage getModel(ResultSet rs) {
		
		CourseManage cm = new CourseManage();
		
		bindModel(cm, rs);
		
		return cm;
		
	}

}
