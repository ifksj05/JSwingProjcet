package comp;

import java.util.List;

import javax.swing.JComboBox;

import base.IRefresh;
import course.Course;
import member.Member;
import member.MemberFactory;
import message.MessageManager;
import provider.PubProivider;

public class MemberList extends JComboBox<Member> implements IRefresh {

	public MemberList() {
		refresh();
	}
	
	@Override
	public void refresh() {
		
		try {
			
			List<Member> mList = PubProivider.getModelList(new MemberFactory(), "select  * from member order by membercode");
			
			removeAllItems();
			
			for (Member member : mList) {
				addItem(member);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}

}
