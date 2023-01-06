package course;

import java.sql.ResultSet;

import base.IFactory;

public class CourseFactory implements IFactory<Course> {

	@Override
	public void bindModel(Course model, ResultSet rs) {
		
		try {
			
			model.setCourseName(rs.getString("courseName"));
			model.setPrice(rs.getInt("price"));
			model.setTeachName(rs.getString("teachName"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Course getModel(ResultSet rs) {
		
		Course c = new Course();
		
		bindModel(c, rs);
		
		return c;
		
	}

}
