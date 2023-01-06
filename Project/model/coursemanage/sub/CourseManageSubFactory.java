package coursemanage.sub;

import java.sql.ResultSet;

import base.IFactory;
import coursemanage.CourseManageFactory;
import message.MessageManager;

public class CourseManageSubFactory implements IFactory<CourseManageSub> {

	@Override
	public void bindModel(CourseManageSub model, ResultSet rs) {
		
		try {
			
			CourseManageFactory cmf = new CourseManageFactory();
			cmf.bindModel(model, rs);
			
			model.setCnt(rs.getInt("cnt"));
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		} 
		
	}

	@Override
	public CourseManageSub getModel(ResultSet rs) {
		
		CourseManageSub cms = new CourseManageSub();
		
		bindModel(cms, rs);
		
		return cms;
		
	}

}
