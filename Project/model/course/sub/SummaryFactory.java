package course.sub;

import java.sql.ResultSet;

import base.IFactory;
import message.MessageManager;

public class SummaryFactory implements IFactory<Summary> {

	@Override
	public void bindModel(Summary model, ResultSet rs) {
		
		try {
			
			model.setCourseName(rs.getString("courseName"));
			model.setPrice(rs.getInt("price"));
			model.setCount(rs.getInt("count"));
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}

	@Override
	public Summary getModel(ResultSet rs) {
		
		Summary s = new Summary();
		
		bindModel(s, rs);
		
		return s;
		
	}

}
