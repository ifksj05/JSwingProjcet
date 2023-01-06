package comp;

import java.util.List;

import javax.swing.JComboBox;

import base.IRefresh;
import course.Course;
import course.CourseFactory;
import message.MessageManager;
import provider.PubProivider;

public class CourseList extends JComboBox<Course> implements IRefresh {

	public CourseList() {
		refresh();
	}
	
	@Override
	public void refresh() {
		
		try {
			
			List<Course> cList = PubProivider.getModelList(new CourseFactory(), "select  * from course order by coursename asc");
			
			removeAllItems();
			
			for (Course course : cList) {
				addItem(course);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}

}
