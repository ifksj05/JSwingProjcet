package jdbc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.util.StringTokenizer;

import message.MessageManager;
import res.ResManager;

public class DbCreateManager {

	public void createAll() throws Exception {
		
		try (DbManager dbMgr = new DbManager("")) {
			
			createDB(dbMgr);
			importData(dbMgr);
			
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void createDB(DbManager dbMgr) throws Exception {

		try (BufferedReader bufRdr = new BufferedReader(
				new InputStreamReader(ClassLoader.getSystemResourceAsStream("res/res/settingdb.txt"), "utf-8"))) {

			String line = "";
			
			while (bufRdr.ready()) {
				line += bufRdr.readLine() + "\n";
			}
			
			dbMgr.getUpdateResult(line);
			
		} catch (Exception e) {
			throw e;
		}

	}

	public void importData(DbManager dbMgr) throws Exception { 
		
		String[] fileNames = new String[] {"admin", "member", "course", "crhistory"};
		String[] sqls = new String[] {"?,?,?,1,0,now()", "?,?,?,?,?", "?,?,?", "?,?,?,?,?,?,?,?"};
		
		for (int i = 0; i < sqls.length; i++) {
			importData(dbMgr, fileNames[i], sqls[i]);
		}
		
	}
	
	public void importData(DbManager dbMgr, String fileName, String sql) throws Exception {
		
		File file = new File(ResManager.RES_PATH + fileName + ".txt");
		
		if (file.exists()) {
			
			int rowCnt = 0;
			
			try (BufferedReader bufRdr = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"))) {
				
				while (bufRdr.ready()) {
					
					String line = bufRdr.readLine();
					
					if (rowCnt > 0) {
						
						StringTokenizer token = new StringTokenizer(line, "\t");
						
						int colCnt = 1;
						PreparedStatement pStmt = dbMgr.getPrepStmt("insert into " + (fileName.equals("crhistory") ? "coursemanage" : fileName) + " values(" + sql + ")");
						
						while (token.hasMoreTokens()) {
							
							if (fileName.equals("admin") && colCnt == 4) {
								break;
							}
							
							String tokenStr = token.nextToken();
							tokenStr = tokenStr.replace("\uFEFF", "");
							
							pStmt.setObject(colCnt++, tokenStr);
							
						}
						
						pStmt.executeUpdate();
						
					}
					
					rowCnt++;
					
					
				}
			} catch (Exception e) {
				throw e;
			}
			

		}
		
	}
	
}
