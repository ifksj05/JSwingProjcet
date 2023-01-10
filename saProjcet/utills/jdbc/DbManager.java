package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class DbManager {
	private String URL = "jdbc:mysql://localhost/sa_project?" + "CharacterEncoding=UTF-8&" + "serverTimezone=UTC&"
			+ "allowPublicKeyRetrieval=true&" + "allowLoadLocalInfile=true&" + "allowMultiQueries=true";
	private String USER = "root";
	private String PW = "1234";
	private Connection con;
	private PreparedStatement pstmt;

	public DbManager() {
		try {
			con = DriverManager.getConnection(URL, USER, PW);
			System.out.println("연결 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int setDb(String sql, Object... values) {
		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;

			for (Object value : values) {
				pstmt.setObject(cnt++, value);
			}

			System.out.println("setDb 성공");

			return pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("setDb 실패");
			return -1;
		}
	}

	public Vector<Vector<String>> getDb(String sql, Object... values) {

		Vector<Vector<String>> data = new Vector<Vector<String>>();

		try {
			pstmt = con.prepareStatement(sql);

			int cnt = 1;

			for (Object value : values) {
				pstmt.setObject(cnt++, value);
			}

			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {

				Vector<String> row = new Vector<String>();

				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					row.add(rs.getObject(i) + "");
				}

				data.add(row);

			}

			System.out.println("getDb 성공");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getDb 실패");
			return null;
		}
		return data;
	}

}
