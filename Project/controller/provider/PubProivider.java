package provider;

import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import base.IFactory;
import jdbc.DbManager;

public class PubProivider {
	
	public static List getModelList(IFactory factory, String sql, Object...objects) throws Exception {
		
		List modelList = new Vector<>();
		
		try (DbManager dbMgr = new DbManager()) {
			
			ResultSet rs = dbMgr.getResultSet(sql, objects);
			
			while (rs.next()) {
				modelList.add(factory.getModel(rs));
			}
			
		} catch (Exception e) {
			throw e;
		}
		
		return modelList;
		
	}
	
}
