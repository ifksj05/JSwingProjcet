package app;

import jdbc.DbCreateManager;
import message.MessageManager;

public class MainClass {

	public static void main(String[] args) {
		
		try {
			
			DbCreateManager mgr = new DbCreateManager();
			mgr.createAll();
				
			MessageManager.showMsg("세팅이 완료되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			MessageManager.showErr(e.getMessage());
		}
		
	}

}
