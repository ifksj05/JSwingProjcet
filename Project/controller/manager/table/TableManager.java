package manager.table;

import java.util.List;

import javax.swing.table.DefaultTableModel;

import base.IFactory;
import base.IModel;
import provider.PubProivider;

public class TableManager {
	
	public static void clearRows(DefaultTableModel jtModel) {
		
		while (jtModel.getRowCount() > 0) {
			
			jtModel.removeRow(0);
			
		}
		
	}
	
	public static List fillRows(DefaultTableModel jtModel, IFactory factory, String sql, Object...objects) throws Exception {
		
		try {
			
			clearRows(jtModel);
			
			List<IModel> modelList = PubProivider.getModelList(factory, sql, objects);
			
			for (IModel iModel : modelList) {
				jtModel.addRow(iModel.getObjectArray());
			}
			
			return modelList;
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
}
