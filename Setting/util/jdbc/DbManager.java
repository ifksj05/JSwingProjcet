package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbManager implements AutoCloseable {

	private static final String CONNECTION_FRONT = "jdbc:mysql://localhost/";
	
	private static final String DB_NAME = "Institute";
	private static final String DB_USER_ID = "root";
	private static final String DB_USER_PWD = "1234";
	
	private Connection con;
	
	public DbManager() throws Exception {
		this(DB_NAME);
	}
	
	public DbManager(String dbName) throws Exception {
		
		try {
			
			con = DriverManager.getConnection(CONNECTION_FRONT + dbName + "?useSSL=false&allowMultiQueries=true&rewriteBatchedStatements=true", DB_USER_ID, DB_USER_PWD);
			
		} catch (Exception e) {
			throw new Exception("DB에 연결할 수 없습니다.");
		}
		
	}

	public PreparedStatement getPrepStmt(String sql, Object...objects) throws Exception {
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		for (int i = 0; i < objects.length; i++) {
			pStmt.setObject(i + 1, objects[i]);
		}
		
		return pStmt;
		
	}
	
	public ResultSet getResultSet(String sql, Object...objects) throws Exception {
		
		return getPrepStmt(sql, objects).executeQuery();
		
	}

	public boolean getUpdateResult(String sql, Object...objects) throws Exception {
		
		return getPrepStmt(sql, objects).executeUpdate() > 0;
		
	}
	
	@Override
	public void close() throws Exception {

		try {

			if (con != null) {
				con.close();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
