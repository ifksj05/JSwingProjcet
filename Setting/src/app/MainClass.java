package app;

import jdbc.DbCreateManager;
import message.MessageManager;

public class MainClass {

	public static void main(String[] args) {
		
		try {
			
			DbCreateManager mgr = new DbCreateManager();
			mgr.createAll();
				
			MessageManager.showMsg("������ �Ϸ�Ǿ����ϴ�.");
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}

}
