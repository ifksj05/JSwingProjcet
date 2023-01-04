package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbManager {
	private String URL = "jdbc:mysql://localhost/?" + "CharacterEncoding=UTF-8&" + "serverTimezone=UTC&"
			+ "allowPublicKeyRetrieval=true&" + "allowLoadLocalInfile=true&" + "allowMultiQueries=true";
	private String ID = "root";
	private String PW = "1234";

	private Connection con;
	private PreparedStatement pstmt;

	public DbManager() {
		try {
			con = DriverManager.getConnection(URL, ID, PW);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int setData(String sql, Object... values) {
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;
			for (Object value : values) {
				pstmt.setObject(cnt++, value);
			}

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
